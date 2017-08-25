package com.imgscoop.scoops;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="Scoop_Tag")
public class Tag {
	@Id
	@Column(name="tag_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	@Column(name="tag_type")
	private String tag;
	@ManyToMany(mappedBy="tags")
	private List<Thread> threads;

	public Tag() {
		super();
	}

	public Tag(int id, String tag, List<Thread> threads) {
		super();
		this.id = id;
		this.tag = tag;
		this.threads = threads;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public List<Thread> getThreads() {
		return threads;
	}

	public void setThreads(List<Thread> threads) {
		this.threads = threads;
	}

}
