package com.imgscoop.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.imgscoop.scoops.Tag;
import com.imgscoop.scoops.Thread;

@Repository
public class ThreadDAOImpl implements ThreadDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void create(Thread thread) {
		sessionFactory.getCurrentSession().save(thread);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(int id) {
		  Thread thread = (Thread) sessionFactory.getCurrentSession().createCriteria(Thread.class)
                  .add(Restrictions.eq("id", id)).uniqueResult();
		  sessionFactory.getCurrentSession().delete(thread);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void update(Thread thread) {
		sessionFactory.getCurrentSession().saveOrUpdate(thread);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Thread> findByAll() {
		return sessionFactory.getCurrentSession().createCriteria(Thread.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Thread> findByTitle(String title) {
		return sessionFactory.getCurrentSession().createCriteria(Thread.class).add(Restrictions.eq("title", title))
				.list();
	}

	@Transactional
	public List<Thread> findByTag(List<Tag> tags) {
		// TODO
		return null;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Thread> findByPage(int page) {
		if(page >= 0) 
			page *= 10;
		return sessionFactory.getCurrentSession().createQuery("from Thread").setFirstResult(page).setMaxResults(10)
				.list();
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Thread> findById(int id) {
		return sessionFactory.getCurrentSession().createCriteria(Thread.class).add(Restrictions.eq("id", id)).list();
	}
}
