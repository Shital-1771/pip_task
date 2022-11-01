package com.example.blog;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.blog.repository.UserRepo;

@SpringBootTest
class DemoApplicationTests {
	
	
	@Autowired
	private UserRepo userRepo;

	@Test
	void contextLoads() {
	}
	
	@Test
	public void userRepoTest()
	{
		 String classname=this.userRepo.getClass().getName();
		 String packagename=this.userRepo.getClass().getPackageName();		
		 System.out.println(classname);
		 System.out.println(packagename);
		 
	}

}
