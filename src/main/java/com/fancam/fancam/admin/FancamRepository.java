package com.fancam.fancam.admin;

import com.fancam.fancam.model.FancamInfoDto;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface FancamRepository extends JpaRepository<FancamInfoDto,Long> {
}
