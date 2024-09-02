package com.fancam.fancam.user.folder;

import com.fancam.fancam.model.folder.FolderInfoDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FolderRepository extends JpaRepository<FolderInfoDto,Long> {

    List<FolderInfoDto> findByUserIdx(Long userIdx);
}
