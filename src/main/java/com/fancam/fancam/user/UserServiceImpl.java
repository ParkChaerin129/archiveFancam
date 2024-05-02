package com.fancam.fancam.user;

import com.fancam.fancam.model.folder.FolderInfoDto;
import com.fancam.fancam.model.folder.FolderingInfoDto;
import com.fancam.fancam.model.folder.FolderingInfoDtoId;
import com.fancam.fancam.model.UserInfoDto;
import com.fancam.fancam.model.like.LikeInfoDto;
import com.fancam.fancam.model.like.LikeInfoDtoId;
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
    public Long createNewUser(String nickname, String name, String email, String pwd) {
        UserInfoDto userInfoDto = UserInfoDto.builder().nickname(nickname).name(name)
                .email(email).pwd(pwd).build();
        Long id = userDao.createNewUserToDB(userInfoDto);
        return id;
    }






}
