package com.fancam.fancam;

import com.fancam.fancam.admin.*;
import com.fancam.fancam.admin.FancamRepository;
import com.fancam.fancam.admin.TagRepository;
import com.fancam.fancam.admin.TaggingRepository;
import com.fancam.fancam.model.tag.TagInfoDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

//@SpringBootTest
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class FancamApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void commitTest(){
		System.out.println("잘 연결되었나용?");
		// 한글 테스트
	}


	@Autowired
    FancamRepository fancamRepository;

	@Test
	@Rollback(value = false)
	void createNewFancam(){
		/*
		FancamInfoDto fancamInfoDto = FancamInfoDto.builder().
										Name("쇼음악중심3월3일 인블룸").
										date("2023-03-03").
										member("박건욱").
										FancamUrl("http://test").
										status("ACTIVE").
				build();

		fancamRepository.save(fancamInfoDto);*/
		//ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
		//AdminService adminService = ac.getBean(AdminServiceImpl.class);

		AppConfig appConfig = new AppConfig(fancamRepository, tagRepository,taggingRepository);
		AdminService adminService = appConfig.adminService();
		//AdminService adminService = new AdminServiceImpl(fancamRepository, new AdminDao(fancamRepository));
		//AdminService adminService = ac.getBean(AdminServiceImpl.class);
		ArrayList<String> tagNames = new ArrayList<>();
		tagNames.add("흑발");
		tagNames.add("청남방");
		tagNames.add("체크남방");
		System.out.println("-----------------------------");

		boolean result=adminService.createNewFancam("뮤직뱅크 6월 7일 멜팅포인트","2023-06-07","박건욱","http://testGasi",tagNames);
		System.out.println("result = " + result);
		System.out.println("-----------------------------");
	}



	@Autowired
	TagRepository tagRepository;

	@Autowired
	TaggingRepository taggingRepository;

	@Test
	@Rollback(value = false)
	void createNewTagName(){

		//TagInfoDto tagInfoDto = TagInfoDto.builder().tagName("흑발").build();
		//tagRepository.save(tagInfoDto);
		AppConfig appConfig = new AppConfig(fancamRepository, tagRepository,taggingRepository);
		System.out.println("-----------------------------");
		AdminService adminService = appConfig.adminService();
		//boolean result = adminService.createNewTag("청남방");
		//System.out.println("result = " + result);
		System.out.println("-----------------------------");


	}

	@Test
	@Rollback(value = false)
	void findTagByTagName(){

		System.out.println("----------------------------------");
		TagInfoDto tagInfoDto = tagRepository.findByTagIdx(1L).orElse(null);
		System.out.println("tagInfoDto.getTagIdx() = " + tagInfoDto.getTagName());

		TagInfoDto tagInfoDto2 = tagRepository.findByTagName("덮머").orElse(null);
		System.out.println("tagInfoDto2.getTagIdx() = " + tagInfoDto2.getTagIdx());

		System.out.println("----------------------------------");
	}

	@Test
	@Rollback(value = false)
	void findTagsByTagNames() throws Exception {

		AppConfig appConfig = new AppConfig(fancamRepository, tagRepository,taggingRepository);
		AdminDao adminDao = appConfig.adminDao();

		ArrayList<String> tagNames = new ArrayList<>();
		tagNames.add("흑발");
		tagNames.add("청남방");
		tagNames.add("안경");

		ArrayList<Long> result=adminDao.getTagNameIdxArray(tagNames);

		System.out.println("----------------------------------");
		System.out.print("result = ");
		for(Long e : result){
			System.out.print(e+" , ");
		}
		System.out.println();
		System.out.println("----------------------------------");


	}

	@Test
	@Rollback(value = false)
	void createTagging() throws Exception {

		AppConfig appConfig = new AppConfig(fancamRepository, tagRepository,taggingRepository);
		AdminDao adminDao = appConfig.adminDao();

		ArrayList<String> tagNames = new ArrayList<>();
		tagNames.add("흑발");
		tagNames.add("청남방");
		tagNames.add("안경");

		ArrayList<Long> result=adminDao.getTagNameIdxArray(tagNames);

		System.out.println("----------------------------------");
		System.out.print("result = ");
		for(Long e : result){
			System.out.print(e+" , ");
		}
		System.out.println();
		System.out.println("----------------------------------");

		adminDao.createNewTagging(2L,result);
	}

}
