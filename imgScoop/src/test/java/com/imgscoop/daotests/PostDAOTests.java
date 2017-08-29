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
	private static User adminUser;
	private static User ordUser;
	// Thread used for readBy*Tests
	private static Thread dummyThread;
	// Thread and post used for the create/delete tests
	private static Thread cdThread;
	private static Post cdPost;
	
	@BeforeClass
	public static void initTests(){
		context = new ClassPathXmlApplicationContext("spring-config.xml");
		dao = (PostDAO) context.getBean("postDAO");
		adminUser = new User(1, "bigboss", "bigboss@motherbase.net", new Role(1, "Admin"), null);
		ordUser = new User(2, "kmillz", "millersama@motherbase.net", new Role(2, "User"), null);
		dummyThread = new Thread(1, null, "Dummy Thread", null);
		cdThread = new Thread(2, null, "Free Hamburgers on Deck 1", null);
		cdPost = new Post(adminUser, "Good dang old hambagahs", new byte[1], cdThread, new Timestamp(System.currentTimeMillis()));
	}

	@Test
	public void createTest(){
		System.out.println("Before "+cdPost);
		dao.create(cdPost);
		System.out.println("After "+cdPost);
		assertEquals(5, dao.getAll().size());
	}
	
	@Test
	public void getByThreadTest(){
		List<Post> posts = dao.getByThread(dummyThread);
		assertEquals(2, posts.size());
	}
	
	@Test
	public void getByUserTest(){
		List<Post> posts = dao.getByUser(ordUser);
		assertEquals(2, posts.size());
	}
	
	@Test
	public void deleteTest() {
		dao.delete(cdPost);
		assertEquals(4, dao.getAll().size());
	}
	
}
