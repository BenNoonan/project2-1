package com.imgscoop.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.imgscoop.scoops.Post;
import com.imgscoop.scoops.Tag;
import com.imgscoop.scoops.Thread;

public class ThreadDAOImpl implements ThreadDAO{
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED,
			rollbackFor=Exception.class)
	public void create(Thread thread) {
		sessionFactory.getCurrentSession().save(thread);
	}

	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED,
			rollbackFor=Exception.class)
	public void delete(Thread thread) {
		sessionFactory.getCurrentSession().delete(thread);
	}

	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED,
			rollbackFor=Exception.class)
	public void update(Thread thread) {
		sessionFactory.getCurrentSession().saveOrUpdate(thread);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Thread> findByAll() {
		return sessionFactory.getCurrentSession().createCriteria(Thread.class).list();
		//return sessionFactory.getCurrentSession().createQuery("from scoop_thread").list();
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Thread> findByTitle(String title) {
		return sessionFactory.getCurrentSession().createQuery("from Thread where thread_title=:title")
				.setParameter("title", title).list();
	}

	@Transactional
	public List<Thread> findByTag(List<Tag> tags) {
		//TODO
		return null;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Thread> findByPage(int page) {
		return sessionFactory.getCurrentSession().createQuery("from Thread")
				.setFirstResult(page).setMaxResults(15).list();
	}
}
