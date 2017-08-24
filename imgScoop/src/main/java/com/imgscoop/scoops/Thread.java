package com.imgscoop.scoops;

public class Thread {

	private int id;
	private Tag tags;
	private String title;
	private Post posts;
	
	public Thread() {
		super();
	}
	public Thread(int id, Tag tags, String title, Post posts) {
		super();
		this.id = id;
		this.tags = tags;
		this.title = title;
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
	public Post getPosts() {
		return posts;
	}
	public void setPosts(Post posts) {
		this.posts = posts;
	}
	@Override
	public String toString() {
		return "Thread [id=" + id + ", tags=" + tags + ", title=" + title + ", posts=" + posts + "]";
	}
	
	
}
