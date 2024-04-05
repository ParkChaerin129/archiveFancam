package com.fancam.fancam.admin;

import com.fancam.fancam.model.FancamInfoDto;
import com.fancam.fancam.model.TagInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminDao {


    private final FancamRepository fancamRepository;
    private final TagRepository tagRepository;

    @Autowired
    public AdminDao(FancamRepository fancamRepository, TagRepository tagRepository) {
        this.fancamRepository = fancamRepository;
        this.tagRepository = tagRepository;
    }


    public Long createNewFancamToDB(FancamInfoDto fancamInfoDto) throws Exception{

        FancamInfoDto save = fancamRepository.save(fancamInfoDto);
        return save.getFancamIdx();

    }

    public Long createNewTagToDB(TagInfoDto tagInfoDto) throws Exception{
        TagInfoDto save = tagRepository.save(tagInfoDto);
        return save.getTagIdx();
    }

}
