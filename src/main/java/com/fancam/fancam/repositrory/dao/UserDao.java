package com.fancam.fancam.repositrory.dao;


import com.fancam.fancam.model.UserInfoDto;
import com.fancam.fancam.repositrory.repository.UserRepository;
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
