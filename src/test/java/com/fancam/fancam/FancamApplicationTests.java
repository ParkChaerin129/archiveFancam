package com.fancam.fancam;

import com.fancam.fancam.admin.AdminServiceImpl;
import com.fancam.fancam.admin.FancamRepository;
import com.fancam.fancam.model.FancamInfoDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
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
	void createNewFancam(){
		FancamInfoDto fancamInfoDto = FancamInfoDto.builder().
										Name("뮤직뱅크3월2일 인블룸").
										date("2023-03-02").
										member("박건욱").
										FancamUrl("http://test").
										status("ACTIVE").
				build();

		fancamRepository.save(fancamInfoDto);

	}

}
