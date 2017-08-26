package com.imgscoop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.imgscoop.dao.PostDAO;

@Controller
public class PostController {

	@Autowired
	private PostDAO dao;
	
	public void setDao(PostDAO dao){
		this.dao = dao;
	}
	
}
