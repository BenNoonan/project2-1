package com.imgscoop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imgscoop.dao.PostDAO;

@Controller
public class PostController {

	@Autowired
	private PostDAO dao;
	
	public void setDao(PostDAO dao){
		this.dao = dao;
	}
	
	@RequestMapping(value="/test/{m}", method=RequestMethod.GET)
	@ResponseBody
	public String test(@PathVariable("m") String m){
		return "I like "+ m;
	}
	
}
