package com.imgscoop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.imgscoop.dao.PostDAO;
import com.imgscoop.scoops.Post;

/**
 * The only two useful functions for posts are creating and deleting. Since, the
 * threads do the heavy lifting, posts don't have much else to do.
 * 
 * @author Cobian
 *
 */
@Service
public class PostService {

	@Autowired
	private PostDAO dao;

	public void setDao(PostDAO dao) {
		this.dao = dao;
	}

	/**
	 * TODO: Assumes that the post already contains the user and the thread objects.
	 * Change it to expect the user/author in the session, so that we can do
	 * more authorization stuff. (Mainly that if you're not signed in, you can't
	 * post). Add the return for if a create fails 
	 * 
	 */
	public ResponseEntity<Void> create(Post post) {
		dao.create(post);
		return new ResponseEntity<Void>(HttpStatus.I_AM_A_TEAPOT);
	}

	/**
	 * TODO: Add authorization. Mainly so that if you're not signed in to an
	 * admin account, you can't delete posts
	 * 
	 */
	public ResponseEntity<Void> delete(Post post) {
		try {
			dao.delete(post);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			System.out.println("Exception space");
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<List<Post>> findByUsername(String username) {
		return new ResponseEntity<List<Post>>(dao.getByUsername(username), HttpStatus.OK);
	}

}
