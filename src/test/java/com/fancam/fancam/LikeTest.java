package com.fancam.fancam;

import com.fancam.fancam.user.like.LikeService;
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
public class LikeTest {

    ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);


    @Test
    @Rollback(value = false)
    void existLike(){
        LikeService likeService = ac.getBean(LikeService.class);
        likeService.createNewLike(10L,2L);
    }

    @Test
    @Rollback(value = false)
    void unLike(){
        LikeService likeService = ac.getBean(LikeService.class);
        likeService.inactiveLike(10L,2L);
    }
}
