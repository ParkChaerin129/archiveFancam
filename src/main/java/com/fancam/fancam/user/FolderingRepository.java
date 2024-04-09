package com.fancam.fancam.user;

import com.fancam.fancam.model.folder.FolderingInfoDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FolderingRepository extends JpaRepository<FolderingInfoDto,Long> {
}
