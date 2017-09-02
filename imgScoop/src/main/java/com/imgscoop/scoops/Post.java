package com.imgscoop.scoops;

import java.sql.Timestamp;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Scoop_Post")
public class Post {
	@Id
	@Column(name = "post_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne
	private User user;
	@Column(name = "contents")
	private String body;
	@Column(name = "image_data")
	private byte[] image;
	@ManyToOne
	@JoinColumn(nullable = false, name = "thread_id")
	@JsonIgnore
	private Thread thread;
	@Column
	private Timestamp submitted;

	public Post() {
		super();
	}

	public Post(int id, User user, String body, byte[] image, Thread thread, Timestamp submitted) {
		super();
		this.id = id;
		this.user = user;
		this.body = body;
		this.image = image;
		this.thread = thread;
		this.submitted = submitted;
	}
	
	public Post(User user, String body, byte[] image, Thread thread, Timestamp submitted) {
		super();
		this.user = user;
		this.body = body;
		this.image = image;
		this.thread = thread;
		this.submitted = submitted;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

	public Timestamp getSubmitted() {
		return submitted;
	}

	public void setSubmitted(Timestamp submitted) {
		this.submitted = submitted;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", user=" + user.getUsername() + ", body=" + body + ", image=" + Arrays.toString(image)
				+ ", thread=" + thread.getTitle() + ", submitted=" + submitted + "]";
	}

}
