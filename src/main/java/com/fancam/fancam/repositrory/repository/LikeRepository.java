package com.fancam.fancam.repositrory.repository;

import com.fancam.fancam.model.like.LikeInfoDto;
import com.fancam.fancam.model.like.LikeInfoDtoId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<LikeInfoDto,LikeInfoDtoId> {
    Optional<LikeInfoDto> findById(LikeInfoDtoId likeInfoDtoId);
    List<LikeInfoDto> findByLikeInfoDtoId_Useridx(Long userIdx);
}
