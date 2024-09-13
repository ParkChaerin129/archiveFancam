package com.fancam.fancam;

import com.fancam.fancam.service.AdminService;
import com.fancam.fancam.service.UserService;
import com.fancam.fancam.service.FolderService;
import com.fancam.fancam.service.FolderingService;
import com.fancam.fancam.service.LikeService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AutowiredTest {


    //@Autowired
    //FancamRepository fancamRepository;
    //@Autowired
    //TaggingRepository taggingRepository;
    //@Autowired
    //TagRepository tagRepository;

    ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
    @Test
    void autowiredTest(){

        AdminService bean = ac.getBean(AdminService.class);
        System.out.println("bean = " + bean);

        ArrayList<String> tagNames = new ArrayList<>();
        tagNames.add("흑발");
        tagNames.add("핑크머리");
        tagNames.add("체크남방");
        System.out.println("-----------------------------");

        bean.createNewFancam("유라유라4월5일","2024-04-05","석매튜","http://ura",tagNames);


    }

    @Test
    @Rollback(value = false)
    void userCreateTest(){
        UserService userService = ac.getBean(UserService.class);
        userService.createNewUser("쿼카","박채린","quokka@zb1","abcd1234","admin");
    }

    @Test
    @Rollback(value = false)
    void inactiveTagTest() throws Exception{
        AdminService adminService = ac.getBean(AdminService.class);
        adminService.inactiveTag(12L);
    }

    @Test
    @Rollback(value = false)
    void inactiveTaggingTest() throws Exception{
        AdminService adminService = ac.getBean(AdminService.class);
        adminService.inactiveTagging(1L,2L);
    }

    @Test
    @Rollback(value = false)
    void folderCreateTest(){
        UserService userService = ac.getBean(UserService.class);
        FolderService folderService = ac.getBean(FolderService.class);
        folderService.createNewFolder(1L,"멜팅포인트");
    }

    @Test
    @Rollback(value = false)
    void folderInactiveTest(){
        FolderService folderService = ac.getBean(FolderService.class);
        folderService.inactiveFolder(2L);
    }

    @Test
    @Rollback(value = false)
    void folderingCreateTest(){
        UserService userService = ac.getBean(UserService.class);
        FolderingService folderingService = ac.getBean(FolderingService.class);
        folderingService.createNewFoldering(10L,1L);
    }

    @Test
    @Rollback(value = false)
    void folderingInactiveTest(){
        FolderingService folderingService = ac.getBean(FolderingService.class);
        folderingService.inactiveFoldering(10L,1L);
    }


    @Test
    @Rollback(value = false)
    void likeCreateTest(){
        UserService userService = ac.getBean(UserService.class);
        LikeService likeService = ac.getBean(LikeService.class);
        likeService.createNewLike(10L,2L);

    }

    @Test
    @Rollback(value = false)
    void inactiveFancamTest() throws Exception{
        AdminService adminService = ac.getBean(AdminService.class);
        adminService.inactiveFancam(2L);
    }

    @Test
    @Rollback(value = false)
    void inactiveUserTest(){
        UserService userService = ac.getBean(UserService.class);
        userService.inactiveUser(1L);
    }


}
