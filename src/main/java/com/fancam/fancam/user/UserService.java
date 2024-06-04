package com.fancam.fancam.user;

public interface UserService {

    Long createNewUser(String nickname,String name,String email,String pwd, String grade);
    void inactiveUser(Long userIdx);





}
