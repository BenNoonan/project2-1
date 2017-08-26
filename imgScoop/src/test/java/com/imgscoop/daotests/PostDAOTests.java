package com.imgscoop.daotests;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.List;

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
	private static Thread testThread1;
	private static Post testPost1;
	
	@BeforeClass
	public static void initTests(){
		context = new ClassPathXmlApplicationContext("spring-config.xml");
		dao = (PostDAO) context.getBean("postDAO");
		testUser = new User(1,"BBOSS", "BIGBOSS@MOTHERBASE.NET",new Role(1,"ADMIN"), null);
		testThread = new Thread(1, null, "Free Hamburgers on Deck 1", null);
		testPost = new Post(1, testUser, "Kaz made some good ass hamburgers. Come get 'em while they're hot", new byte[1], testThread, new Timestamp(System.currentTimeMillis()));
		testThread1 = new Thread(2, null, "Anyone seen Quiet?", null);
		testPost1 = new Post(2, testUser, "I knwo she's invisible but c'mon", new byte[1], testThread1, new Timestamp(System.currentTimeMillis()));
	}

	@Test
	public void createTest(){
		dao.create(testPost);
		dao.create(testPost1);
		assertEquals(2, dao.getAll().size());
	}
	
	@Test
	public void getByThreadTest(){
		List<Post> posts = dao.getByThread(testThread);
		System.out.println(posts);
		assertEquals(1, posts.size());
	}
	
	@Test
	public void getByAuthorTest(){
		List<Post> posts = dao.getByUser(testUser);
		assertEquals(2, posts.size());
	}
	
	@Test
	public void deleteTest() {
		dao.delete(testPost);
		dao.delete(testPost1);
		assertEquals(0, dao.getAll().size());
	}
	
}
