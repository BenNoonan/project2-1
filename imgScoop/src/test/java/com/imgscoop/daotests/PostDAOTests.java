package com.imgscoop.daotests;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.sql.Timestamp;

import com.imgscoop.dao.PostDAO;
import com.imgscoop.scoops.Post;
import com.imgscoop.scoops.Role;
import com.imgscoop.scoops.User;
import com.imgscoop.scoops.Thread;

public class PostDAOTests {
	
	private static PostDAO dao;
	private static AbstractApplicationContext context;
	private static User testUser;
	private static Thread testThread;
	private static Post testPost;
	
	@BeforeClass
	public static void initTests(){
		context = new ClassPathXmlApplicationContext("spring-config.xml");
		dao = (PostDAO) context.getBean("postDAO");
		testUser = new User(1,"BBOSS", "BIGBOSS@MOTHERBASE.NET",new Role(1,"ADMIN"), null);
		testThread = new Thread(1, null, "Free Hamburgers on Deck 1", null);
		testPost = new Post(1, testUser, "Kaz made some good ass hamburgers. Come get 'em while they're hot", new byte[1], testThread, new Timestamp(System.currentTimeMillis()));
	}

	@Test
	public void createTest(){
		dao.create(testPost);
	}
	
	@Test
	public void deleteTest() {
		dao.delete(testPost);
	}
	
}
