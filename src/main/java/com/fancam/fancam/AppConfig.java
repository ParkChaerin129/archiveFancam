package com.fancam.fancam;

import com.fancam.fancam.admin.*;
import org.springframework.beans.factory.annotation.Autowired;

public class AppConfig {

    @Autowired
    final FancamRepository fancamRepository;
    @Autowired
    final TagRepository tagRepository;

    public AppConfig(FancamRepository fancamRepository, TagRepository tagRepository) {
        this.fancamRepository = fancamRepository;
        this.tagRepository = tagRepository;
    }


    public AdminService adminService(){
        return new AdminServiceImpl(adminDao(), fancamRepository, tagRepository);
    }

    public AdminDao adminDao(){
        return new AdminDao(fancamRepository, tagRepository);
    }


}
