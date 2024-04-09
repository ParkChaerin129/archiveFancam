package com.fancam.fancam.user;


import com.fancam.fancam.model.folder.FolderInfoDto;
import com.fancam.fancam.model.folder.FolderingInfoDto;
import com.fancam.fancam.model.UserInfoDto;
import com.fancam.fancam.model.like.LikeInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDao {

    final UserRepository userRepository;
    final FolderRepository folderRepository;
    final FolderingRepository folderingRepository;
    final LikeRepository likeRepository;

    @Autowired
    public UserDao(UserRepository userRepository, FolderRepository folderRepository,FolderingRepository folderingRepository
    ,LikeRepository likeRepository) {
        this.userRepository = userRepository;
        this.folderRepository = folderRepository;
        this.folderingRepository = folderingRepository;
        this.likeRepository = likeRepository;
    }

    public Long createNewUserToDB(UserInfoDto userInfoDto){

        UserInfoDto newUserInfoDto=userRepository.save(userInfoDto);
        return newUserInfoDto.getUserIdx();

    }

    public Long createNewFolderToDB(FolderInfoDto folderInfoDto){
        FolderInfoDto newFolderInfoDto=folderRepository.save(folderInfoDto);
        return newFolderInfoDto.getFolderIdx();
    }

    public void createNewFolderingToDB(FolderingInfoDto folderingInfoDto){
        folderingRepository.save(folderingInfoDto);
    }

    public void createNewLikeToDB(LikeInfoDto likeInfoDto){
        LikeInfoDto newLikeInfoDto = likeRepository.save(likeInfoDto);

    }

}
