package com.imgscoop.dao;

import java.util.List;

import com.imgscoop.scoops.Tag;
import com.imgscoop.scoops.Thread;

public interface ThreadDAO {
	
	public void create(Thread thread);
	public void delete(Thread thread);
	public void update(Thread thread);
	
	public List<Thread> findByAll();
	public List<Thread> findByPage(int page);
	public List<Thread> findByTitle(String title);
	public List<Thread> findByTag(List<Tag> tags);
	public List<Thread> findById(int id);
}
