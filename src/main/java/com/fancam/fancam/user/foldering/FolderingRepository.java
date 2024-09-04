package com.fancam.fancam.user.foldering;

import com.fancam.fancam.model.folder.FolderingInfoDto;
import com.fancam.fancam.model.folder.FolderingInfoDtoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FolderingRepository extends JpaRepository<FolderingInfoDto, FolderingInfoDtoId> {

    List<FolderingInfoDto> findByFolderingInfoDtoId_Folderidx(Long folderIdx);
}
