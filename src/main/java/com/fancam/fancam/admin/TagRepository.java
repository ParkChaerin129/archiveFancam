package com.fancam.fancam.admin;

import com.fancam.fancam.model.TagInfoDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<TagInfoDto,Long> {


}
