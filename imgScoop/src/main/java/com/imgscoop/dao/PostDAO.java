package com.imgscoop.dao;

import java.util.List;

import com.imgscoop.scoops.Post;
import com.imgscoop.scoops.User;
import com.imgscoop.scoops.Thread;

public interface PostDAO {

	public void create(Post post);

	public void delete(Post post);

	public void update(Post post);

	public List<Post> getByThread(Thread thread);

	public List<Post> getByUser(User author);

}
