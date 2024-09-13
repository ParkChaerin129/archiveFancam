package com.fancam.fancam.service;

import com.fancam.fancam.model.folder.FolderInfoDto;

import java.util.List;


public interface FolderService {

    Long createNewFolder(Long userIdx,String folderName);
    Long inactiveFolder(Long folderIdx);
    List<FolderInfoDto> getFolderList(Long userIdx);

}
