package com.imgscoop.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.imgscoop.scoops.Post;
import com.imgscoop.scoops.Role;
import com.imgscoop.scoops.Thread;
import com.imgscoop.scoops.User;


public class UserDAOImpl implements UserDAO {
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED,
			rollbackFor=Exception.class)
	public void create(User user) {
		sessionFactory.getCurrentSession().save(user);
	}

	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED,
			rollbackFor=Exception.class)
	public void update(User user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
	}

	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED,
			rollbackFor=Exception.class)
	public void delete(User user) {
		sessionFactory.getCurrentSession().delete(user);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<User> findByAll() {
		return sessionFactory.getCurrentSession().createCriteria(User.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}

	@Transactional
	public User findByUsername(String username) {
		return (User) sessionFactory.getCurrentSession().createCriteria(User.class)
				.add(Restrictions.eq("username", username)).list().get(0);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<User> findByRole(Role role) {
		return sessionFactory.getCurrentSession().createCriteria(User.class).add(Restrictions.eq("role", role)).list();
	}

}
