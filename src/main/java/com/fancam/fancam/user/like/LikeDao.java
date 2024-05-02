package com.fancam.fancam.user.like;

import com.fancam.fancam.model.like.LikeInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LikeDao {

    final LikeRepository likeRepository;

    public void createNewLikeToDB(LikeInfoDto likeInfoDto){
        LikeInfoDto newLikeInfoDto = likeRepository.save(likeInfoDto);

    }

}
