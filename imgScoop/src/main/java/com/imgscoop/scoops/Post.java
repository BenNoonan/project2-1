package com.imgscoop.scoops;

import java.sql.Blob;
import java.sql.Timestamp;

public class Post {
	private int id;
	private int threadId;
	private User author;
	private String body;
	private Blob image;
	private Timestamp submitted;

	public Post() {
		super();
	}

	public Post(int id, int threadId, User author, String body, Blob image, Timestamp submitted) {
		super();
		this.id = id;
		this.threadId = threadId;
		this.author = author;
		this.body = body;
		this.image = image;
		this.submitted = submitted;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getThreadId() {
		return threadId;
	}

	public void setThreadId(int threadId) {
		this.threadId = threadId;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Blob getImage() {
		return image;
	}

	public void setImage(Blob image) {
		this.image = image;
	}

	public Timestamp getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Timestamp submitted) {
		this.submitted = submitted;
	}

}
