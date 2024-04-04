package com.fancam.fancam.admin;

import com.fancam.fancam.model.FancamInfoDto;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl{ //implements AdminService{

    AdminDao adminDao = new AdminDao();
/*
    @Override
    public boolean createNewFancam(FancamInfoDto fancamInfoDto) {

        String name = fancamInfoDto.getName();
        String date = fancamInfoDto.getDate();
        String member = fancamInfoDto.getMember();
        String url = fancamInfoDto.getFancamUrl();

        Integer id = adminDao.createNewFancamToDB(name,date,member,url);
        return true;
        //return false;
    }*/
}
