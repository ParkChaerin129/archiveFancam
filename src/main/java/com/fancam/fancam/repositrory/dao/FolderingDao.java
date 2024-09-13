package com.fancam.fancam.repositrory.dao;

import com.fancam.fancam.model.folder.FolderingInfoDto;
import com.fancam.fancam.model.folder.FolderingInfoDtoId;
import com.fancam.fancam.repositrory.repository.FolderingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FolderingDao {

    final FolderingRepository folderingRepository;

    public void saveFolderingToDB(FolderingInfoDto folderingInfoDto){
        folderingRepository.save(folderingInfoDto);
    }

    public boolean isPresentFoldering(FolderingInfoDtoId folderingInfoDtoId){
        Optional<FolderingInfoDto> foldering = folderingRepository.findById(folderingInfoDtoId);
        return foldering.isPresent();
    }

    public List<Long> getFancamIdByFolderId(Long folderId){
        List<FolderingInfoDto> folderings = folderingRepository.findByFolderingInfoDtoId_Folderidx(folderId);
        List<Long> fancamIds = new ArrayList<>();
        for (FolderingInfoDto folderingInfoDto : folderings) {
            if(folderingInfoDto.getStatus().equals("ACTIVE")){
                fancamIds.add(folderingInfoDto.getFolderingInfoDtoId().getFancamidx());
            }
        }
        return fancamIds;
    }

}
