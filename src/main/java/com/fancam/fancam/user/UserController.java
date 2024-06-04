package com.fancam.fancam.user;

import com.fancam.fancam.JwtUtil;
import com.fancam.fancam.model.UserInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public String register(@RequestBody UserInfoDto userInfoDto){
        userService.registerUser(userInfoDto.getNickname(), userInfoDto.getName(),userInfoDto.getEmail(), userInfoDto.getPwd(), userInfoDto.getGrade());
        return "User registered successfully";
    }

    @PostMapping("/login")
    public String login(@RequestBody UserInfoDto userInfoDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userInfoDto.getEmail(), userInfoDto.getPwd())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtUtil.generateToken(userInfoDto.getEmail());
    }

}
