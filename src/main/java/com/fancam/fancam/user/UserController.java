package com.fancam.fancam.user;

import com.fancam.fancam.JwtUtil;
import com.fancam.fancam.model.UserInfoDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

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



}
