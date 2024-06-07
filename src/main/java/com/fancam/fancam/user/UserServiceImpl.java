package com.fancam.fancam.user;

import com.fancam.fancam.model.UserInfoDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    final UserDao userDao;
    final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }



    @Override
    public Long createNewUser(String nickname, String name, String email, String pwd,String grade) {
        UserInfoDto userInfoDto = UserInfoDto.builder().nickname(nickname).name(name)
                .email(email).pwd(pwd).grade(grade).status("ACTIVE").build();
        Long id = userDao.saveUserToDB(userInfoDto);
        return id;
    }

    @Override
    public void inactiveUser(Long userIdx) {
        UserInfoDto userInfoDto = userDao.getUserFromDB(userIdx);
        userInfoDto.setStatus("INACTIVE");
        userDao.saveUserToDB(userInfoDto);
    }

    @Override
    public Long registerUser(String nickname, String name, String email, String pwd, String grade) {

        if(userDao.isPresentUserByEmail(email)){
            throw new RuntimeException("Email already exist");
        }

        UserInfoDto userInfoDto = UserInfoDto.builder().nickname(nickname).name(name)
                .email(email).pwd(passwordEncoder.encode(pwd)).grade(grade).status("ACTIVE").build();
        Long id = userDao.saveUserToDB(userInfoDto);

        return id;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        UserInfoDto user = userDao.getUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String role = "ROLE_"+user.getGrade().toUpperCase();
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPwd(), List.of(()-> role));
    }

}
