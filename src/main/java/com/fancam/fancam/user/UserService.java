package com.fancam.fancam.user;

import com.fancam.fancam.model.UserInfoDto;

public interface UserService {

    Long createNewUser(String nickname,String name,String email,String pwd, String grade);
    void inactiveUser(Long userIdx);

    Long registerUser(String nickname,String name,String email,String pwd, String grade);



}
