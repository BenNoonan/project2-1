package com.imgscoop.scoops;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="THREAD")
public class Thread {

	@Id
	@Column(name="THREAD_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@ManyToMany(mappedBy="thread")
	private Tag tags;
	@Column(name="TITLE")
	private String title;
	@OneToMany(mappedBy="thread")
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
