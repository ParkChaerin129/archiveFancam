package com.fancam.fancam.admin;

import com.fancam.fancam.model.TagInfoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface TagRepository extends JpaRepository<TagInfoDto,Long> {
    Optional<TagInfoDto> findByTagIdx(Long tagIdx);
    Optional<TagInfoDto> findByTagName(String tagName);


}
