package com.fancam.fancam.admin;

import com.fancam.fancam.model.FancamInfoDto;

import java.util.ArrayList;

public interface AdminService {

    boolean createNewFancam(String name, String date, String member, String url, ArrayList<String> tagNames);
    //boolean createNewTag(String tagName);
    void inactiveTag(Long tagIdx);
    void inactiveTagging(Long tagIdx, Long fancamIdx);
    void inactiveFancam(Long fancamIdx);

}
