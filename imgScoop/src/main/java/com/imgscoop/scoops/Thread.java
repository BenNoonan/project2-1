package com.imgscoop.scoops;

import java.util.List;

public class Thread {

	private int id;
	private Tag tags;
	private String title;
	private List<Post> posts;
	
	public Thread() {
		super();
	}
	public Thread(int id, Tag tags, String title, List<Post> posts) {
		super();
		this.id = id;
		this.tags = tags;
		this.title = title;
		this.posts = posts;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Tag getTags() {
		return tags;
	}
	public void setTags(Tag tags) {
		this.tags = tags;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "Thread [id=" + id + ", tags=" + tags + ", title=" + title + ", posts=" + posts + "]";
	}
	
	
	
}
