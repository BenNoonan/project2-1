package com.imgscoop.daotests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.imgscoop.dao.UserDAO;
import com.imgscoop.scoops.Post;
import com.imgscoop.scoops.Role;
import com.imgscoop.scoops.Thread;
import com.imgscoop.scoops.User;

public class UserDAOTest {

	private static AbstractApplicationContext context;
	private static UserDAO dao;
	private static User user, userUpdate;
	private static Thread thread;
	private static Post post;
	private static List<User> u;

	@BeforeClass
	public static void initTest(){
		context = new ClassPathXmlApplicationContext("spring-config.xml");
		dao = (UserDAO) context.getBean("userDAO");
		user = new User("User test", "hacker1337@hotmail.com", "password", new Role(2,"User"), null);
		userUpdate = new User("User Update test", "hacker1337@hotmail.com", "password", new Role(2,"User"), null);
		thread = new Thread(1, null, "Why you shouldn't code for c++", null);
		post = new Post(1, user, "Obviously if you code for c++ then you are wrong!", new byte[1], thread, new Timestamp(System.currentTimeMillis()));
	}
	
	@Test
	public void createUser(){
		dao.create(user);
		assertEquals(3, dao.findByAll().size());
	}
	
	@Test
	public void updateUser(){
		dao.update(user);
		assertEquals(user.getUsername().toString(), "User Update test");
	}
	
	@Test
	public void deleteUser(){
		dao.delete(user);
		assertEquals(2, dao.findByAll().size());
	}
	
	@Test
	public void allUser(){
		List<User> userList = dao.findByAll();
		assertEquals(3, userList.size());
	}
	
	@Test
	public void usernameUser(){
		User userName = dao.findByUsername("kmillz");
		assertEquals(userName.getUsername(), "kmillz");
	}
	
	@Test
	public void roleUser(){
		List<User> userRole = dao.findByRole(new Role(1, "Admin"));
		assertEquals(userRole.get(0).getUsername(), "bigboss");
	}
}
