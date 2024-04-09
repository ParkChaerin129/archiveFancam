package com.fancam.fancam.admin;

import com.fancam.fancam.model.FancamInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AdminServiceImpl implements AdminService{



    final FancamRepository fancamRepository;
    final AdminDao adminDao;
    final TagRepository tagRepository;
    final TaggingRepository taggingRepository;

    @Autowired
    public AdminServiceImpl(AdminDao adminDao, FancamRepository fancamRepository,  TagRepository tagRepository, TaggingRepository taggingRepository) {
        this.fancamRepository = fancamRepository;
        this.adminDao = adminDao;
        this.tagRepository = tagRepository;
        this.taggingRepository = taggingRepository;
    }

    @Override
    public boolean createNewFancam(String name, String date, String member, String url, ArrayList<String> tagNames) {

        FancamInfoDto fancamInfoDto = FancamInfoDto.builder().
                Name(name).
                date(date).
                member(member).
                fancam_url(url).
                status("ACTIVE").
                build();

        Long id;

        try{
            id = adminDao.createNewFancamToDB(fancamInfoDto);
            System.out.println("id = " + id);
        }
        catch(Exception e){
            System.out.println("e = " + e);
            return false;
        }

        ArrayList<Long> tagIdxs = new ArrayList<>();

        try {
            tagIdxs = adminDao.getTagNameIdxArray(tagNames);
        }catch (Exception e){
            System.out.println("e = " + e);
            return false;
        }

        try{
            adminDao.createNewTagging(id,tagIdxs);
        }catch (Exception e){
            System.out.println("e = " + e);
            return false;
        }

        return true;
    }

    /*
    @Override
    public boolean createNewTag(String tagName) {

        TagInfoDto tagInfoDto = TagInfoDto.builder().tagName(tagName).build();

        try{
            Long id = adminDao.createNewTagToDB(tagInfoDto);
            System.out.println("id = " + id);
        }catch (Exception e){
            System.out.println("e = " + e);
            return false;
        }
        return true;
    }*/
}
