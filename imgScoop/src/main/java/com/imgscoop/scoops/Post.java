package com.imgscoop.scoops;

import java.sql.Timestamp;

public class Post {
	private int id;
	private int threadId;
	private User author;
	private String body;
	private byte[] image;
	private Timestamp submitted;

	public Post() {
		super();
	}

	public Post(int id, int threadId, User author, String body, byte[] image, Timestamp submitted) {
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

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Timestamp getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Timestamp submitted) {
		this.submitted = submitted;
	}

}
