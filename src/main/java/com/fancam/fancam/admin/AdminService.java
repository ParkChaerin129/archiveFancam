package com.fancam.fancam.admin;

import com.fancam.fancam.model.FancamInfoDto;

public interface AdminService {

    boolean createNewFancam(String name,String date,String member,String url);
    boolean createNewTag(String tagName);

}
