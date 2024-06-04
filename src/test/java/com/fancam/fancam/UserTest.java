package com.fancam.fancam;


import com.fancam.fancam.user.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserTest {

    ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);

    UserService userService = ac.getBean(UserService.class);

    @Test
    @Rollback(value = false)
    void newUser(){
        userService.registerUser("채롱이","박채롱","abc@zb1","abc","admin");
    }
}
