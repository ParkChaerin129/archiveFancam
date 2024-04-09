package com.fancam.fancam.user;


import com.fancam.fancam.model.UserInfoDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserInfoDto,Long> {

}
