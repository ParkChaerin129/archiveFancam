package com.fancam.fancam.repositrory.repository;


import com.fancam.fancam.model.UserInfoDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserInfoDto,Long> {
    Optional<UserInfoDto> findByEmail(String email);
}
