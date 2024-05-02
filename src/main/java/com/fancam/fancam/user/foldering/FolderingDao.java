package com.fancam.fancam.user.foldering;

import com.fancam.fancam.model.folder.FolderingInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FolderingDao {

    final FolderingRepository folderingRepository;

    public void createNewFolderingToDB(FolderingInfoDto folderingInfoDto){
        folderingRepository.save(folderingInfoDto);
    }

}
