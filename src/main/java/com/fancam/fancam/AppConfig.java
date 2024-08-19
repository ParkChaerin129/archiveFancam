package com.fancam.fancam;

import com.fancam.fancam.admin.*;
import com.fancam.fancam.search.FancamRepository;
import com.fancam.fancam.search.TagRepository;
import com.fancam.fancam.search.TaggingRepository;
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
        return new AdminServiceImpl(adminDao());
    }

    public AdminDao adminDao(){
        return new AdminDao(fancamRepository, tagRepository,taggingRepository);
    }


}
