package com.fancam.fancam.admin;

import com.fancam.fancam.model.FancamInfoDto;
import com.fancam.fancam.model.TagInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class AdminServiceImpl implements AdminService{



    final FancamRepository fancamRepository;
    final AdminDao adminDao;
    final TagRepository tagRepository;

    @Autowired
    public AdminServiceImpl(AdminDao adminDao, FancamRepository fancamRepository,  TagRepository tagRepository) {
        this.fancamRepository = fancamRepository;
        this.adminDao = adminDao;
        this.tagRepository = tagRepository;
    }

    @Override
    public boolean createNewFancam(String name,String date,String member,String url) {

        FancamInfoDto fancamInfoDto = FancamInfoDto.builder().
                Name(name).
                date(date).
                member(member).
                fancam_url(url).
                status("ACTIVE").
                build();


        try{
            Long id = adminDao.createNewFancamToDB(fancamInfoDto);
            System.out.println("id = " + id);
        }
        catch(Exception e){
            System.out.println("e = " + e);
            return false;
        }
        return true;
    }

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
    }
}
