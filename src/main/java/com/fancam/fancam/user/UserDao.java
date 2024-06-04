package com.fancam.fancam.user;


import com.fancam.fancam.model.UserInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public final class UserDao {


    final UserRepository userRepository;


    public Long saveUserToDB(UserInfoDto userInfoDto){

        UserInfoDto newUserInfoDto=userRepository.save(userInfoDto);
        return newUserInfoDto.getUserIdx();

    }

    public UserInfoDto getUserFromDB(Long userIdx){
        Optional<UserInfoDto> userInfoDto = userRepository.findById(userIdx);
        return userInfoDto.get();
    }

    public boolean isPresentUserByEmail(String email){
        Optional<UserInfoDto> userInfoDto = userRepository.findByEmail(email);
        return userInfoDto.isPresent();
    }

    public Optional<UserInfoDto> getUserByEmail(String email){
        Optional<UserInfoDto> userInfoDto = userRepository.findByEmail(email);
        return userInfoDto;
    }




}
