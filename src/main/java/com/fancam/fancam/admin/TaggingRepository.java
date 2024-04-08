package com.fancam.fancam.admin;

import com.fancam.fancam.model.TaggingInfoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface TaggingRepository extends JpaRepository<TaggingInfoDto,Long> {
}
