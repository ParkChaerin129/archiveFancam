package com.fancam.fancam.user.folder;

import com.fancam.fancam.model.folder.FolderInfoDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface FolderService {

    Long createNewFolder(Long userIdx,String folderName);
    Long inactiveFolder(Long folderIdx);
    List<FolderInfoDto> getFolderList(Long userIdx);

}
