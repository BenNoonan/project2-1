package com.imgscoop.dao;

import java.util.List;

import com.imgscoop.scoops.Post;
import com.imgscoop.scoops.Thread;

public interface PostDAO {

	public void create(Post post);

	public void delete(int id);

	public void update(Post post);
	
	public long countRows();

	public List<Post> getByUsername(String author);
	
	public List<Post> getAll();

	public List<Post> getByThread(Thread thread);

}
