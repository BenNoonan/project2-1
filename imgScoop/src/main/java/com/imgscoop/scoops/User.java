package com.imgscoop.scoops;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="USER")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="USER_ID")
	private int id;
	@Column(name="USER_USERNAME")
	private String username;
	@Column(name="USER_EMAIL")
	private String email;
	@Column(name="USER_PASSWORD")
	private String password;
	@Column(name="USER_ROLE")
	@OneToMany(mappedBy="user")
	private Role role;
	@Column(name="USER_POSTS")
	@OneToMany(mappedBy="user")
	private List<Post> posts;
	
	public User() {
		super();
	}
	/*
	 * TODO implementation with JDBCrypt for password initiation
	 */
	public User(int id, String username, String email, String password, Role role, List<Post> posts) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.role = role;
		this.posts = posts;
	}
	public User(int id, String username, String email, Role role, List<Post> posts) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.role = role;
		this.posts = posts;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", role=" + role + ", posts=" + posts
				+ "]";
	}
	
	
}
