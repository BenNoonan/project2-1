package com.imgscoop.daotests;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.imgscoop.dao.ThreadDAO;
import com.imgscoop.scoops.Post;
import com.imgscoop.scoops.Role;
import com.imgscoop.scoops.Thread;
import com.imgscoop.scoops.User;

public class ThreadDAOTest {
	
	private static AbstractApplicationContext context;
	private static ThreadDAO dao;
	private static User user;
	private static Thread thread;
	private static Post post;
	private static List<Thread> t;

	@BeforeClass
	public static void initTest(){
		context = new ClassPathXmlApplicationContext("spring-config.xml");
		dao = (ThreadDAO) context.getBean("threadDAO");
		user = new User(1,"Pupper234", "doggo@hotmail.com",new Role(2,"USER"), null);
		thread = new Thread(37, null, "Dogs vs Cats, Which is better? *Edit, Neither", null);
		post = new Post(1, user, "I think that dogs are way better; nothing to due with my name.", new byte[1], thread, new Timestamp(System.currentTimeMillis()));
	}
	
	@Test
	@Ignore
	//create a thread
	public void createThread(){
		dao.create(thread);
		assertEquals(1, dao.findByAll().size());
	}
	
	@Test
	@Ignore
	//delete a thread by id
	public void deleteThread(){
		dao.delete(thread);
	}
	
	@Test
	@Ignore
	//update a thread
	public void updateThread(){
		dao.update(thread);
	}
	
	@Test
	@Ignore
	//findByAll for thread
	public void allThread(){
		 t = dao.findByAll();
		System.out.println(t);
		assertEquals(3, t.size());
	}
	
	@Test
	@Ignore
	//findByTitle for thread
	public void titleThread(){
		t = dao.findByTitle("Free Hamburgers on Deck 1");
		System.out.println(t);
		assertEquals(1, t.size());
	}
	
	@Test
	//findByPage for thread
	public void pageThread(){
		t = dao.findByPage(1);
		System.out.println(t);
		assertEquals(2, t.size());
	}
}
