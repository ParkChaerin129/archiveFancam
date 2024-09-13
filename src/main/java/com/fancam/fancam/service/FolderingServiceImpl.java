package com.fancam.fancam.service;

import com.fancam.fancam.model.folder.FolderingInfoDto;
import com.fancam.fancam.model.folder.FolderingInfoDtoId;
import com.fancam.fancam.model.search.SearchDto;
import com.fancam.fancam.repositrory.dao.FolderingDao;
import com.fancam.fancam.repositrory.dao.SearchDao;
import com.fancam.fancam.repositrory.dao.LikeDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FolderingServiceImpl implements FolderingService {

    final FolderingDao folderingDao;
    final SearchDao searchDao;
    final LikeDao likeDao;

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

    @Override
    public List<SearchDto> getFolderFancamList(Long userIdx,Long folderIdx) {
        List<Long> fancamIds;
        if(folderIdx==0){
            fancamIds=likeDao.getLikedList(userIdx);
        }
        else{
            fancamIds = folderingDao.getFancamIdByFolderId(folderIdx);
        }
        List<SearchDto> fancamList = new ArrayList<>();
        for(Long id : fancamIds){
            SearchDto searchDto = searchDao.searchFancamFromDBByFancamIdx(id);
            fancamList.add(searchDto);
        }

        return fancamList;
    }
}
