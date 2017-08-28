package com.imgscoop.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import com.imgscoop.scoops.User;

public class UserDAOImpl implements UserDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void create(User user) {
		// TODO Auto-generated method stub

	}

	public void update(User user) {
		// TODO Auto-generated method stub

	}

	public void delete(User user) {
		// TODO Auto-generated method stub

	}

	public List<User> findByAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public User findByUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<User> findByRole() {
		// TODO Auto-generated method stub
		return null;
	}

}
