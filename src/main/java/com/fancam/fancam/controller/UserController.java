package com.fancam.fancam.controller;

import com.fancam.fancam.JwtUtil;
import com.fancam.fancam.model.UserInfoDto;
import com.fancam.fancam.model.folder.FolderInfoDto;
import com.fancam.fancam.model.search.SearchDto;
import com.fancam.fancam.service.FolderService;
import com.fancam.fancam.service.FolderingService;
import com.fancam.fancam.service.LikeService;
import com.fancam.fancam.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private LikeService likeService;

    @Autowired
    private FolderService folderService;

    @Autowired
    private FolderingService folderingService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Value("${secret.key}")
    private String secret;

    @PostMapping("/register")
    public String register(@RequestBody UserInfoDto userInfoDto){
        userService.registerUser(userInfoDto.getNickname(), userInfoDto.getName(),userInfoDto.getEmail(), userInfoDto.getPwd(), userInfoDto.getGrade());
        return "User registered successfully";
    }

    @PostMapping("/login")
    public String login(@RequestBody UserInfoDto userInfoDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userInfoDto.getEmail(), userInfoDto.getPwd()));
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .claim("authorities", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 864_000_000)) // 10 days
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

    }

    @GetMapping("/auth")
    public Boolean getAuthorization(@RequestHeader("Authorization") String token) {

        String jwtToken = token.replace("Bearer ", "");

        Claims claims = Jwts.parser()
                .setSigningKey(secret)  // JWT 서명 검증을 위한 키
                .parseClaimsJws(jwtToken)  // 토큰 파싱
                .getBody();             // 페이로드(클레임) 가져오기

        // authorities 클레임에서 권한 리스트 확인
        List<String> authorities = (List<String>) claims.get("authorities");

        // ADMIN 권한이 있는지 확인
        return authorities != null && authorities.contains("ROLE_ADMIN");
    }

    @PostMapping("/like/{fancamIdx}")
    public String like(@RequestHeader("Authorization") String token,@PathVariable Long fancamIdx){
        boolean flag;

        try{
            Long userIdx = getUserIdByToken(token);
            likeService.createNewLike(fancamIdx,userIdx);
            return "success";
        }
        catch (Exception e){return "false";}
    }

    @GetMapping("/like/{fancamIdx}")
    public String isLike(@RequestHeader("Authorization") String token,@PathVariable Long fancamIdx){

        try{
            Long userIdx = getUserIdByToken(token);
            if(likeService.isLiked(fancamIdx,userIdx)){
                return "true";
            }else{
                return "false";
            }
        }
        catch (Exception e){
            return "fail";
        }

    }

    @PatchMapping("/like/{fancamIdx}")
    public String cancelLike(@RequestHeader("Authorization") String token,@PathVariable Long fancamIdx){

        try{
            Long userIdx = getUserIdByToken(token);
            likeService.inactiveLike(fancamIdx,userIdx);
            return "success";
        }
        catch (Exception e){return "false";}
    }

    @PostMapping("/folder")
    public String newFolder(@RequestHeader("Authorization") String token,@RequestBody Map<String,Object> requestBody){

        Long userIdx = getUserIdByToken(token);
        String folderName = requestBody.get("folderName").toString();
        folderService.createNewFolder(userIdx,folderName);
        return "success";
    }

    @GetMapping("/folder")
    public List<FolderInfoDto> getFolder(@RequestHeader("Authorization") String token){
        try{
            Long userIdx = getUserIdByToken(token);
            return folderService.getFolderList(userIdx);
        }
        catch (Exception e){return List.of();}
    }

    @PostMapping("/folder/{fancamIdx}")
    public String addFancamToFolder(@PathVariable Long fancamIdx,@RequestBody Map<String,Object> requestBody){
        Long folderIdx = Long.valueOf(requestBody.get("folderIdx").toString());
        folderingService.createNewFoldering(fancamIdx,folderIdx);
        return "success";
    }

    @GetMapping("/my/folder/{folderIdx}")
    public List<SearchDto> getMyFolderFancam(@RequestHeader("Authorization") String token,@PathVariable Long folderIdx){
        Long userIdx = getUserIdByToken(token);
        return folderingService.getFolderFancamList(userIdx,folderIdx);
    }

    @PatchMapping("/my/folder/{folderIdx}/{fancamIdx}")
    public String inactiveFoldering(@RequestHeader("Authorization") String token,@PathVariable Long fancamIdx,@PathVariable Long folderIdx){
        Long userIdx = getUserIdByToken(token);
        if(folderIdx==0){
            likeService.inactiveLike(fancamIdx,userIdx);
        }else{
            folderingService.inactiveFoldering(fancamIdx, folderIdx);
        }
        return "success";
    }

    private Long getUserIdByToken(String token) {
        try {
            // "Bearer " 접두사를 제거하여 실제 JWT 토큰을 가져옴
            String jwtToken = token.replace("Bearer ", "");

            // JWT를 파싱하고 검증하여 클레임을 추출
            Claims claims = Jwts.parser()
                    .setSigningKey(secret) // 비밀 키로 서명 검증
                    .parseClaimsJws(jwtToken) // 토큰을 파싱하여 클레임을 얻음
                    .getBody();

            // 클레임에서 사용자 ID 추출 (예: "sub" 클레임에 사용자 ID 저장)
            String userName = claims.getSubject(); // 클레임에서 'sub'를 통해 사용자 ID를 추출

            // 사용자 ID를 사용해 사용자 정보 조회
            // Long 타입으로 변환하여 조회

            return userService.getUserIdByUserName(userName);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



}
