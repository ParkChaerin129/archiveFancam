package com.fancam.fancam.user.foldering;

import com.fancam.fancam.model.folder.FolderingInfoDto;
import com.fancam.fancam.model.folder.FolderingInfoDtoId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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

}
