package com.fancam.fancam.user.folder;

import org.springframework.stereotype.Service;


public interface FolderService {

    Long createNewFolder(Long userIdx,String folderName);

}
