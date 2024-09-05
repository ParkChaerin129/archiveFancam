package com.fancam.fancam.admin;

import com.fancam.fancam.model.FancamInfoDto;
import com.fancam.fancam.model.tag.TagInfoDto;
import com.fancam.fancam.model.tag.TaggingInfoDto;
import com.fancam.fancam.model.tag.TaggingInfoDtoId;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{


    final AdminDao adminDao;



    @Override
    public boolean createNewFancam(String name, String date, String member, String url, ArrayList<String> tagNames){



        FancamInfoDto fancamInfoDto = FancamInfoDto.builder().
                name(name).
                date(date).
                member(member).
                fancam_url(url).
                status("ACTIVE").
                build();

        Long id;

        try{
            id = adminDao.saveFancamToDB(fancamInfoDto);
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
            adminDao.saveTaggingByNewFancam(id,tagIdxs);
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

    @Override
    public void inactiveTag(Long tagIdx) throws Exception{
        TagInfoDto tagInfoDto = adminDao.getTagFromDB(tagIdx);
        tagInfoDto.setStatus("INACTIVE");
        try{
            adminDao.saveTagToDB(tagInfoDto);
        }catch (Exception e){
            System.out.println("e = " + e);
            throw e;
        }
    }

    @Override
    public void inactiveTagging(Long tagIdx, Long fancamIdx) throws Exception {
        TaggingInfoDtoId taggingInfoDtoId = new TaggingInfoDtoId();
        taggingInfoDtoId.setFancamidx(fancamIdx);
        taggingInfoDtoId.setTagidx(tagIdx);
        TaggingInfoDto taggingInfoDto = TaggingInfoDto.builder().taggingInfoDtoId(taggingInfoDtoId).status("INACTIVE").build();
        try{
            adminDao.saveTagging(taggingInfoDto);
        }catch (Exception e){
            System.out.println("e = " + e);
            throw e;
        }
    }

    @Override
    public void inactiveFancam(Long fancamIdx) throws Exception{
        FancamInfoDto fancamInfoDto = adminDao.getFancamFromDB(fancamIdx);
        fancamInfoDto.setStatus("INACTIVE");
        try{
            adminDao.saveFancamToDB(fancamInfoDto);
        }catch (Exception e){
            System.out.println("e = " + e);
            throw e;
        }
    }
}
