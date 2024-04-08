package com.fancam.fancam;

import com.fancam.fancam.admin.*;
import org.springframework.beans.factory.annotation.Autowired;

public class AppConfig {

    @Autowired
    final FancamRepository fancamRepository;
    @Autowired
    final TagRepository tagRepository;

    @Autowired
    final TaggingRepository taggingRepository;

    public AppConfig(FancamRepository fancamRepository, TagRepository tagRepository, TaggingRepository taggingRepository) {
        this.fancamRepository = fancamRepository;
        this.tagRepository = tagRepository;
        this.taggingRepository=taggingRepository;
    }


    public AdminService adminService(){
        return new AdminServiceImpl(adminDao(), fancamRepository, tagRepository,taggingRepository);
    }

    public AdminDao adminDao(){
        return new AdminDao(fancamRepository, tagRepository,taggingRepository);
    }


}
