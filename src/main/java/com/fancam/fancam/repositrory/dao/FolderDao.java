package com.fancam.fancam.repositrory.dao;

import com.fancam.fancam.model.folder.FolderInfoDto;
import com.fancam.fancam.repositrory.repository.FolderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
@RequiredArgsConstructor
public class FolderDao {

    final FolderRepository folderRepository;

    public Long saveFolderToDB(FolderInfoDto folderInfoDto){
        FolderInfoDto newFolderInfoDto=folderRepository.save(folderInfoDto);
        return newFolderInfoDto.getFolderIdx();
    }

    public FolderInfoDto getFolderFromDB(Long folderIdx){
        Optional<FolderInfoDto> folderInfoDto = folderRepository.findById(folderIdx);
        return folderInfoDto.get();
    }

    public List<FolderInfoDto> getFolderListFromDB(Long userIdx){
        return folderRepository.findByUserIdx(userIdx);
    }

}
