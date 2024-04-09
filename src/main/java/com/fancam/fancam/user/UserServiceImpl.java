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

    @Override
    public Long createNewFolder(Long userIdx, String folderName) {
        FolderInfoDto folderInfoDto = FolderInfoDto.builder().userIdx(userIdx).folderName(folderName).build();
        Long id = userDao.createNewFolderToDB(folderInfoDto);

        return id;
    }

    @Override
    public void createNewFoldering(Long fancamIdx, Long folderIdx) {
        FolderingInfoDtoId folderingInfoDtoId = new FolderingInfoDtoId();
        folderingInfoDtoId.setFancamidx(fancamIdx);
        folderingInfoDtoId.setFolderidx(folderIdx);
        FolderingInfoDto folderingInfoDto = FolderingInfoDto.builder().folderingInfoDtoId(folderingInfoDtoId).build();
        userDao.createNewFolderingToDB(folderingInfoDto);
    }

    @Override
    public void createNewLike(Long fancamIdx, Long userIdx) {
        LikeInfoDtoId likeInfoDtoId = new LikeInfoDtoId();
        likeInfoDtoId.setUseridx(userIdx);
        likeInfoDtoId.setFancamidx(fancamIdx);
        LikeInfoDto likeInfoDto = LikeInfoDto.builder().likeInfoDtoId(likeInfoDtoId).build();

        userDao.createNewLikeToDB(likeInfoDto);
    }
}
