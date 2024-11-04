package com.fancam.fancam.repositrory.repository;

import com.fancam.fancam.model.tag.TaggingInfoDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TaggingRepository extends JpaRepository<TaggingInfoDto,Long> {
    List<TaggingInfoDto> findByTaggingInfoDtoId_Tagidx(Long tagidx);
}
