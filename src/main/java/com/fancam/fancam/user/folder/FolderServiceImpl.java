package com.fancam.fancam.user.folder;

import com.fancam.fancam.model.folder.FolderInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class FolderServiceImpl implements FolderService{

    final FolderDao folderDao;

    @Override
    public Long createNewFolder(Long userIdx, String folderName) {
        FolderInfoDto folderInfoDto = FolderInfoDto.builder().userIdx(userIdx).folderName(folderName).build();
        Long id = folderDao.createNewFolderToDB(folderInfoDto);

        return id;
    }
}
