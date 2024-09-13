package com.fancam.fancam.service;

import com.fancam.fancam.model.like.LikeInfoDto;
import com.fancam.fancam.model.like.LikeInfoDtoId;
import com.fancam.fancam.repositrory.dao.LikeDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    final LikeDao likeDao;

    @Override
    public void createNewLike(Long fancamIdx, Long userIdx) {
        LikeInfoDtoId likeInfoDtoId = new LikeInfoDtoId();
        likeInfoDtoId.setUseridx(userIdx);
        likeInfoDtoId.setFancamidx(fancamIdx);
        LikeInfoDto likeInfoDto = LikeInfoDto.builder().likeInfoDtoId(likeInfoDtoId).status("ACTIVE").build();

        likeDao.saveLikeToDB(likeInfoDto);
    }

    @Override
    public void inactiveLike(Long fancamIdx, Long userIdx){
        LikeInfoDtoId likeInfoDtoId = new LikeInfoDtoId();
        likeInfoDtoId.setFancamidx(fancamIdx);
        likeInfoDtoId.setUseridx(userIdx);
        if(likeDao.isPresentLike(likeInfoDtoId)){
            LikeInfoDto likeInfoDto = LikeInfoDto.builder().likeInfoDtoId(likeInfoDtoId).status("INACTIVE").build();
            likeDao.saveLikeToDB(likeInfoDto);
        }
    }

    @Override
    public boolean isLiked(Long fancamIdx, Long userIdx) {
        LikeInfoDtoId likeInfoDtoId = new LikeInfoDtoId();
        likeInfoDtoId.setFancamidx(fancamIdx);
        likeInfoDtoId.setUseridx(userIdx);
        return likeDao.isLiked(likeInfoDtoId);
    }


}
