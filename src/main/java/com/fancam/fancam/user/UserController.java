package com.fancam.fancam.user;

import com.fancam.fancam.JwtUtil;
import com.fancam.fancam.model.UserInfoDto;
import com.fancam.fancam.user.like.LikeService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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

        /*Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userInfoDto.getEmail(), userInfoDto.getPwd())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return jwtUtil.generateToken(userInfoDto.getEmail(),userDetails);*/
    }

    @PostMapping("/like/{fancamIdx}")
    public String like(@RequestHeader("Authorization") String token,@PathVariable Long fancamIdx){
        boolean flag;

        try{
            Long userIdx = getUserIdByToken(token);
            likeService.createNewLike(fancamIdx,userIdx);
            return "success";
        }
        catch (Exception e){return "False";}
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
