package com.fancam.fancam.user.foldering;

public interface FolderingService {

    void createNewFoldering(Long fancamIdx,Long folderIdx);
    void inactiveFoldering(Long fancamIdx,Long folderIdx);
}
