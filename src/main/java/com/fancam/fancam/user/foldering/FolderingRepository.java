package com.fancam.fancam.user.foldering;

import com.fancam.fancam.model.folder.FolderingInfoDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FolderingRepository extends JpaRepository<FolderingInfoDto,Long> {
}
