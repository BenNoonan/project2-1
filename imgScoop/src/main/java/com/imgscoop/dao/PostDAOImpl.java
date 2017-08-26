package com.imgscoop.dao;

import java.util.List;


import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.imgscoop.scoops.Post;
import com.imgscoop.scoops.Thread;
import com.imgscoop.scoops.User;

@Transactional
public class PostDAOImpl implements PostDAO {

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	public void create(Post post) {
		sessionFactory.getCurrentSession().save(post);
	}

	public void delete(Post post) {
		sessionFactory.getCurrentSession().delete(post);
	}

	public void update(Post post) {
		sessionFactory.getCurrentSession().saveOrUpdate(post);
	}

	@SuppressWarnings("unchecked")
	public List<Post> getByThread(Thread thread) {
		return sessionFactory.getCurrentSession().createQuery("from Post where thread_id=:id").setParameter("id", thread.getId()).list();
	}

	
	@SuppressWarnings("unchecked")
	public List<Post> getByUser(User author) {
		return sessionFactory.getCurrentSession().createQuery("from Post where user_user_id=:id").setParameter("id", author.getId()).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Post> getAll(){
		return sessionFactory.getCurrentSession().createCriteria(Post.class).list();
	}

}
