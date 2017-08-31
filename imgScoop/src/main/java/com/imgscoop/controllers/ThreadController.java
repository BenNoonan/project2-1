package com.imgscoop.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imgscoop.scoops.Thread;
import com.imgscoop.dao.ThreadDAO;

@Controller
public class ThreadController {

	@Autowired
	private ThreadDAO dao;

	public ThreadController(ThreadDAO dao) {
		this.dao = dao;
	}
	@RequestMapping(value="/thread/create", method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void create(@Valid @RequestBody Thread thread){
		dao.create(thread);
	}
	
	@RequestMapping(value="/thread/delete", method=RequestMethod.DELETE,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void delete(@RequestBody Thread thread){
		dao.delete(thread);
	}
	
	@RequestMapping(value="/thread/update", method=RequestMethod.PUT,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void update(@RequestBody Thread thread){
		dao.update(thread);
	}
	
	@RequestMapping(value="/thread/all", method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Thread> findByAll(){
		return dao.findByAll();
	}
	
	@RequestMapping(value="/thread/title", method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Thread> findByTitle(@RequestBody String title){
		return dao.findByTitle(title);
	}
	
	//@ResponseBody
	//public List<Thread> findByTag(){}
	
	@RequestMapping(value="/thread/page", method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Thread> findByPage(int page){
		return dao.findByPage(page);
	}
}
