package com.fancam.fancam.user;

import com.fancam.fancam.model.folder.FolderInfoDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FolderRepository extends JpaRepository<FolderInfoDto,Long> {
}
