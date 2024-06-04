package com.fancam.fancam.user;

import com.fancam.fancam.model.UserInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
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
}
