package com.fancam.fancam;

import com.fancam.fancam.admin.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
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

    @Test
    void autowiredTest(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        AdminService bean = ac.getBean(AdminService.class);
        System.out.println("bean = " + bean);

        ArrayList<String> tagNames = new ArrayList<>();
        tagNames.add("흑발");
        tagNames.add("핑크머리");
        tagNames.add("체크남방");
        System.out.println("-----------------------------");

        bean.createNewFancam("유라유라4월5일","2024-04-05","석매튜","http://ura",tagNames);


    }

}
