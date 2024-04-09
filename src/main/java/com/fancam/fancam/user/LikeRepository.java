package com.fancam.fancam.user;

import com.fancam.fancam.model.like.LikeInfoDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<LikeInfoDto,Long> {
}
