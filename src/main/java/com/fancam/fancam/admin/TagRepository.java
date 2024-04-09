package com.fancam.fancam.admin;

import com.fancam.fancam.model.tag.TagInfoDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface TagRepository extends JpaRepository<TagInfoDto,Long> {
    Optional<TagInfoDto> findByTagIdx(Long tagIdx);
    Optional<TagInfoDto> findByTagName(String tagName);


}
