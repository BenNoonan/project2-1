package com.imgscoop.dao;

import java.util.List;


import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
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
	
	public long countRows(){
		return (Long) sessionFactory.getCurrentSession().createCriteria(Post.class).setProjection(Projections.rowCount()).uniqueResult();
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
	public List<Post> getByUser(User user) {
		return sessionFactory.getCurrentSession().createCriteria(Post.class).add(Restrictions.eq("user", user)).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Post> getAll(){
		return sessionFactory.getCurrentSession().createCriteria(Post.class).list();
	}

	public List<Post> getByThread(Thread thread) {
		// TODO Auto-generated method stub
		return null;
	}

}
