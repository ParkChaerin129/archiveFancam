package com.fancam.fancam.user.foldering;

import com.fancam.fancam.model.folder.FolderingInfoDto;
import com.fancam.fancam.model.folder.FolderingInfoDtoId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FolderingServiceImpl implements FolderingService{

    final FolderingDao folderingDao;

    @Override
    public void createNewFoldering(Long fancamIdx, Long folderIdx) {
        FolderingInfoDtoId folderingInfoDtoId = new FolderingInfoDtoId();
        folderingInfoDtoId.setFancamidx(fancamIdx);
        folderingInfoDtoId.setFolderidx(folderIdx);
        FolderingInfoDto folderingInfoDto = FolderingInfoDto.builder().folderingInfoDtoId(folderingInfoDtoId).status("ACTIVE").build();
        folderingDao.saveFolderingToDB(folderingInfoDto);
    }

    @Override
    public void inactiveFoldering(Long fancamIdx, Long folderIdx) {
        FolderingInfoDtoId folderingInfoDtoId = new FolderingInfoDtoId();
        folderingInfoDtoId.setFancamidx(fancamIdx);
        folderingInfoDtoId.setFolderidx(folderIdx);
        if(folderingDao.isPresentFoldering(folderingInfoDtoId)){
            FolderingInfoDto folderingInfoDto = FolderingInfoDto.builder().folderingInfoDtoId(folderingInfoDtoId).status("INACTIVE").build();
            folderingDao.saveFolderingToDB(folderingInfoDto);
        }

    }
}
