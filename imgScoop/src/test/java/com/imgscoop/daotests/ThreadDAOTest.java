package com.imgscoop.daotests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.imgscoop.dao.ThreadDAO;
import com.imgscoop.scoops.Role;
import com.imgscoop.scoops.Thread;
import com.imgscoop.scoops.User;

public class ThreadDAOTest {
    
    private static AbstractApplicationContext context;
    private static ThreadDAO dao;
    private static User user;
    private static Thread testCreateThread, testReadThread;
    private static int temp;

	@BeforeClass
	public static void initTest(){
		context = new ClassPathXmlApplicationContext("spring-config.xml");
		dao = (ThreadDAO) context.getBean("threadDAO");
		temp = dao.findByAll().size();
		user = new User(0, "bigboss", "bigboss@motherbase.net",new Role(1,"Admin"), null);
		testCreateThread = new Thread(null, "Dogs vs Cats, Which is better?", null);
		testReadThread = new Thread(1, null, "Dummy Thread", null);
	}
	
	@Test
	@Ignore
	//create a thread
	public void createThread(){
		dao.create(testCreateThread);
		List<Thread> threads = dao.findByAll();
		System.out.println(threads);
		assertEquals(3, threads.size());
	}
	
	@Test
	@Ignore
	//delete a thread by id
	public void deleteThread(){
		dao.delete(testCreateThread);
		assertEquals(2,  dao.findByAll().size());
	}
	
	@Test
	@Ignore
	//update a thread
	public void updateThread() {
		Thread expected = dao.findByTitle("Dummy Thread updated").iterator().next();
		expected.setTitle("Updated Thread Title");
		dao.update(expected);
		Thread actual = dao.findByTitle("Updated Thread Title").iterator().next();
		System.out.println(expected);
		System.out.println(actual);
		assertEquals(expected.toString(), actual.toString());
	}
	
	@Test
	@Ignore
	//findByAll for thread
	public void allThread(){
		 List<Thread> threads = dao.findByAll();
		assertEquals(temp, threads.size());
	}
	
	@Test
	@Ignore
	//findByTitle for thread
	//Test not working the best
	public void titleThread(){
		 List<Thread> threads  = dao.findByTitle("Updated Thread Title");
		assertTrue(threads.contains(testCreateThread.getTitle()));
	}
	
	@Test
	@Ignore
	//findByPage for thread
	public void pageThread(){
		List<Thread> threads  = dao.findByPage(0);
		assertEquals(2, threads.size());
	}
	
	@Test
	@Ignore
	//Creating a bunch of threads
	public void multiThreadCreation(){
		for(int i=0; i < 16; i++){
			dao.create(new Thread(null, ("Test " + i), null));
		}
		//List<Thread> threads = dao.findByAll();
		//System.out.println(threads);
		//assertEquals(3, threads.size());
	}
}