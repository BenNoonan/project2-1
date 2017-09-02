package com.imgscoop.dao;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.imgscoop.scoops.Post;
import com.imgscoop.scoops.Thread;

@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
@Repository
public class PostDAOImpl implements PostDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public long countRows() {
		return (Long) sessionFactory.getCurrentSession().createCriteria(Post.class)
				.setProjection(Projections.rowCount()).uniqueResult();
	}

	public void create(Post post) {
		post.setSubmitted(new Timestamp(System.currentTimeMillis()));
		sessionFactory.getCurrentSession().save(post);
	}

	public void delete(Post post) {
		sessionFactory.getCurrentSession().delete(post);
	}

	public void update(Post post) {
		sessionFactory.getCurrentSession().saveOrUpdate(post);
	}

	@SuppressWarnings("unchecked")
	public List<Post> getByUsername(String user) {
		return sessionFactory.getCurrentSession().createCriteria(Post.class).createAlias("user", "u")
				.add(Restrictions.eq("u.username", user)).addOrder(Order.desc("submitted"))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}

	@SuppressWarnings("unchecked")
	public List<Post> getAll() {
		return sessionFactory.getCurrentSession().createCriteria(Post.class).list();
	}

	@SuppressWarnings("unchecked")
  	public List<Post> getByThread(Thread thread) {
		return sessionFactory.getCurrentSession().createCriteria(Post.class).add(Restrictions.eq("thread", thread)).list();
  	}
}
