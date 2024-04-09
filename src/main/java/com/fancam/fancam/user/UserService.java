package com.fancam.fancam.user;

public interface UserService {

    Long createNewUser(String nickname,String name,String email,String pwd);
    Long createNewFolder(Long userIdx,String folderName);
    void createNewFoldering(Long fancamIdx,Long folderIdx);
    void createNewLike(Long fancamIdx, Long userIdx);


}
