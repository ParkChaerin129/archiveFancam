package com.fancam.fancam.user.like;

import com.fancam.fancam.model.like.LikeInfoDto;
import com.fancam.fancam.model.like.LikeInfoDtoId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LikeDao {

    final LikeRepository likeRepository;

    public void saveLikeToDB(LikeInfoDto likeInfoDto){
        LikeInfoDto newLikeInfoDto = likeRepository.save(likeInfoDto);

    }

    public boolean isPresentLike(LikeInfoDtoId likeInfoDtoId){
        Optional<LikeInfoDto> optionalLikeInfoDto = likeRepository.findById(likeInfoDtoId);
        return optionalLikeInfoDto.isPresent();
    }

    public boolean isLiked(LikeInfoDtoId likeInfoDtoId){
        Optional<LikeInfoDto> optionalLikeInfoDto = likeRepository.findById(likeInfoDtoId);
        if(optionalLikeInfoDto.isPresent() && Objects.equals(optionalLikeInfoDto.get().getStatus(), "ACTIVE")){
            return true;
        }else{
            return false;
        }
    }



}
