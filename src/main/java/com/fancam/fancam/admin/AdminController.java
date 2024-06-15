package com.fancam.fancam.admin;


import com.fancam.fancam.model.FancamInfoDto;
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
        try{

            String name = (String) requestBody.get("name");
            String date = (String) requestBody.get("date");
            String member = (String) requestBody.get("member");
            String url = (String) requestBody.get("url");
            ArrayList<String> tagNames = (ArrayList<String>) requestBody.get("tagNames");

            adminService.createNewFancam(name,date,member,url,tagNames);

        }catch (Exception e){
            e.printStackTrace();
            return "fail";
        }


        return "success";
    }

}
