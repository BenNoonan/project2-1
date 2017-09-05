package com.imgscoop.controllers;

import javax.validation.Valid;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.imgscoop.scoops.Post;
import com.imgscoop.scoops.User;
import com.imgscoop.scoops.Thread;
import com.imgscoop.services.PostService;
import com.imgscoop.services.ThreadService;

@Controller
@RequestMapping(value = "/post")
public class PostController {

	@Autowired
	private PostService postService;
	@Autowired
	private ThreadService threadService;

	public void setPostService(PostService postService) {
		this.postService = postService;
	}

	public void setThreadService(ThreadService threadService) {
		this.threadService = threadService;
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Void> create(HttpServletRequest req, @RequestParam String body, @RequestParam int thread)
			throws IOException {
		User author = (User) req.getSession().getAttribute("loggedin");
		Thread parent = threadService.findById(thread).getBody().get(0);
		System.out.println("Heererererer");
		Post post = new Post(author, body, null, parent, new Timestamp(System.currentTimeMillis()));
		return postService.create(post);
	}

	@RequestMapping(value = "/img", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Void> createImg(HttpServletRequest req, @RequestParam MultipartFile image,
			@RequestParam String body, @RequestParam int thread) throws IOException {
		User author = (User) req.getSession().getAttribute("loggedin");
		Thread parent = threadService.findById(thread).getBody().get(0);
		System.out.println(image.getOriginalFilename());
		byte[] img = null;
		try {
			img = image.getBytes();
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		Post post = new Post(author, body, img, parent, new Timestamp(System.currentTimeMillis()));
		System.out.println("with image " + post);
		return postService.create(post);
	}

	@RequestMapping(method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Void> delete(@Valid @RequestBody Post post, HttpServletRequest req) {
		return postService.delete(post);
	}

	@RequestMapping(value = "/user/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Post>> findByUserId(@PathVariable String username) {
		return postService.findByUsername(username);
	}
}
