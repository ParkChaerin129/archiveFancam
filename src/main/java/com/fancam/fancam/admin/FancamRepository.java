package com.fancam.fancam.admin;

import com.fancam.fancam.model.FancamInfoDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FancamRepository extends JpaRepository<FancamInfoDto,Long> {
}
