package com.imgscoop.services;

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
	 * Assumes that the post already contains the user and the thread objects.
	 * Change it to expect the user/author in the session, so that we can do
	 * more authorization stuff. (Mainly that if you're not signed in, you can't
	 * post)
	 * 
	 * @param post
	 * @return
	 */
	public ResponseEntity<Post> create(Post post) {
		dao.create(post);
		return new ResponseEntity<Post>(post, HttpStatus.I_AM_A_TEAPOT);
	}

	/**
	 * Add authorization. Mainly so that if you're not signed in to an
	 * admin account, you can't delete posts
	 * 
	 * @param post
	 * @return
	 */
	public ResponseEntity<Void> delete(Post post) {
		try {
			long before = dao.countRows();
			dao.delete(post);
			long after = dao.countRows();
			if (before == after + 1)
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			System.out.println("Exception space");
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}

}
