package com.imgscoop.dao;

import java.util.List;

import com.imgscoop.scoops.User;

public interface UserDAO {

	public void create(User user);
	public void update(User user);
	public void delete(User user);
	
	public List<User> findByAll();
	public User findByUsername();
	public List<User> findByRole();
}
