package com.imgscoop.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.imgscoop.scoops.Post;
import com.imgscoop.scoops.Thread;
import com.imgscoop.scoops.User;

public class PostDAOImpl implements PostDAO {

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void create(Post post) {
		sessionFactory.getCurrentSession().save(post);
	}

	@Transactional
	public void delete(Post post) {
		sessionFactory.getCurrentSession().delete(post);
	}

	public void update(Post post) {
		// TODO Auto-generated method stub

	}

	public List<Post> getByThread(Thread thread) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Post> getByUser(User author) {
		// TODO Auto-generated method stub
		return null;
	}

}
