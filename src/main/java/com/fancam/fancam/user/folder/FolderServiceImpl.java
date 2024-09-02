package com.fancam.fancam.user.folder;

import com.fancam.fancam.model.folder.FolderInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class FolderServiceImpl implements FolderService{

    final FolderDao folderDao;

    @Override
    public Long createNewFolder(Long userIdx, String folderName) {
        FolderInfoDto folderInfoDto = FolderInfoDto.builder().userIdx(userIdx).folderName(folderName).status("ACTIVE").build();
        Long id = folderDao.saveFolderToDB(folderInfoDto);

        return id;
    }

    @Override
    public Long inactiveFolder(Long folderIdx) {
        FolderInfoDto folderInfoDto = folderDao.getFolderFromDB(folderIdx);
        folderInfoDto.setStatus("INACTIVE");
        return folderDao.saveFolderToDB(folderInfoDto);
    }

    @Override
    public List<FolderInfoDto> getFolderList(Long userIdx) {
        return folderDao.getFolderListFromDB(userIdx);
    }
}
