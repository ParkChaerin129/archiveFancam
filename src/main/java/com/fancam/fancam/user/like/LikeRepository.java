package com.fancam.fancam.user.like;

import com.fancam.fancam.model.like.LikeInfoDto;
import com.fancam.fancam.model.like.LikeInfoDtoId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<LikeInfoDto,LikeInfoDtoId> {
    Optional<LikeInfoDto> findById(LikeInfoDtoId likeInfoDtoId);
}
