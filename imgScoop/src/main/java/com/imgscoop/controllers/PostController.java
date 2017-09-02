package com.imgscoop.controllers;

import javax.validation.Valid;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imgscoop.scoops.Post;
import com.imgscoop.services.PostService;

@Controller
@RequestMapping(value = "/post")
public class PostController {

	@Autowired
	private PostService service;

	public void setService(PostService service) {
		this.service = service;
	}

	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Void> create(@Valid @RequestBody Post post) {
		System.out.println("in the create with " + post);
		return service.create(post);
	}

	@RequestMapping(method=RequestMethod.DELETE, consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Void> delete(@Valid @RequestBody Post post, HttpServletRequest req){
		return service.delete(post);
	}
	
	@RequestMapping(value="/user/{username}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Post>> findByUserId(@PathVariable String username){
		return service.findByUsername(username);
	}
}
