package com.fancam.fancam.user.folder;

import com.fancam.fancam.model.folder.FolderInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class FolderDao {

    final FolderRepository folderRepository;

    public Long createNewFolderToDB(FolderInfoDto folderInfoDto){
        FolderInfoDto newFolderInfoDto=folderRepository.save(folderInfoDto);
        return newFolderInfoDto.getFolderIdx();
    }

}
