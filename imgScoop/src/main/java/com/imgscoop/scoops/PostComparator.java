package com.imgscoop.scoops;

import java.util.Comparator;

public class PostComparator implements Comparator<Post> {

	//Sorts by the submitted timestamp
	public int compare(Post p1, Post p2) {
		return p1.getSubmitted().compareTo(p2.getSubmitted());
	}

}
