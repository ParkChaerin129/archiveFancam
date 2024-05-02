package com.fancam.fancam.user;


import com.fancam.fancam.model.UserInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public final class UserDao {


    final UserRepository userRepository;


    public Long createNewUserToDB(UserInfoDto userInfoDto){

        UserInfoDto newUserInfoDto=userRepository.save(userInfoDto);
        return newUserInfoDto.getUserIdx();

    }






}
