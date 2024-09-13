package com.fancam.fancam.repositrory.repository;

import com.fancam.fancam.model.folder.FolderingInfoDto;
import com.fancam.fancam.model.folder.FolderingInfoDtoId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FolderingRepository extends JpaRepository<FolderingInfoDto, FolderingInfoDtoId> {

    List<FolderingInfoDto> findByFolderingInfoDtoId_Folderidx(Long folderIdx);
}
