package com.fancam.fancam.repositrory.repository;

import com.fancam.fancam.model.tag.TaggingInfoDto;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaggingRepository extends JpaRepository<TaggingInfoDto,Long> {
}
