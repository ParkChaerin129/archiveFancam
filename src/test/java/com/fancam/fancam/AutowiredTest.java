package com.fancam.fancam;

import com.fancam.fancam.admin.*;
import com.fancam.fancam.user.UserDao;
import com.fancam.fancam.user.UserService;
import com.fancam.fancam.user.folder.FolderService;
import com.fancam.fancam.user.foldering.FolderingService;
import com.fancam.fancam.user.like.LikeService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
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
        //UserService userService = ac.getBean(UserService.class);
        //userService.createNewUser("아기토끼","한유진","rabbit@zb1","abcd1234");
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
    void folderingCreateTest(){
        UserService userService = ac.getBean(UserService.class);
        FolderingService folderingService = ac.getBean(FolderingService.class);
        folderingService.createNewFoldering(10L,1L);
    }


    @Test
    @Rollback(value = false)
    void likeCreateTest(){
        UserService userService = ac.getBean(UserService.class);
        LikeService likeService = ac.getBean(LikeService.class);
        likeService.createNewLike(10L,2L);

    }


}
