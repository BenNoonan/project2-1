package com.imgscoop.scoops;

import java.util.Comparator;

public class PostComparator implements Comparator<Post> {

	//Sorts by the id
	public int compare(Post p1, Post p2) {
		return p1.getId() + p2.getId();
	}

}
