package com.fancam.fancam.controller;


import com.fancam.fancam.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {


    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/service")
    public String test(){
        return "success";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping ("/fancam")
    public String newFancam(@RequestBody Map<String,Object> requestBody){
        // String name, String date, String member, String url, ArrayList<String> tagNames

        boolean flag;

        try{

            String name = (String) requestBody.get("name");
            String date = (String) requestBody.get("date");
            String member = (String) requestBody.get("member");
            String url = (String) requestBody.get("url");
            ArrayList<String> tagNames = (ArrayList<String>) requestBody.get("tagNames");

            flag=adminService.createNewFancam(name,date,member,url,tagNames);

        }catch (Exception e){
            e.printStackTrace();
            throw e;
            //return "fail";
        }

        if(flag){
            return "success";
        }
        else{
            return "fail";
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/tag/inactive")
    public String inactiveTag(@RequestBody Map<String,Object> requestBody){

        try{
            String tagIdx = (String) requestBody.get("tagIdx");
            adminService.inactiveTag(Long.valueOf(tagIdx));
        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }

        return "success";

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/tagging/inactive")
    public String inactiveTagging(@RequestBody Map<String,Object> requestBody){
        try{
            String tagIdx = (String) requestBody.get("tagIdx");
            String fancamIdx = (String) requestBody.get("fancamIdx");
            adminService.inactiveTagging(Long.valueOf(tagIdx),Long.valueOf(fancamIdx));
        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
        return "success";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/fancam/inactive")
    public String inactiveFancam(@RequestBody Map<String,Object> requestBody){
        try{
            String fancamIdx = (String) requestBody.get("fancamIdx");
            adminService.inactiveFancam(Long.valueOf(fancamIdx));
        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }
        return "success";
    }

}
