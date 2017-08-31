package com.imgscoop.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imgscoop.scoops.Thread;
import com.imgscoop.dao.ThreadDAO;

@Controller
@RequestMapping(value="/thread")
public class ThreadController {

	@Autowired
	private ThreadDAO dao;

	public void setDao(ThreadDAO dao) {
		this.dao = dao;
	}

	@RequestMapping(method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void create(@Valid @RequestBody Thread thread){
		dao.create(thread);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void delete(@RequestBody Thread thread){
		dao.delete(thread);
	}
	
	@RequestMapping(method=RequestMethod.PUT,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void update(@RequestBody Thread thread){
		dao.update(thread);
	}
	
	@RequestMapping(value="/all", method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Thread> findByAll(){
		return dao.findByAll();
	}
	
	@RequestMapping(value="/title", method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Thread> findByTitle(@RequestBody String title){
		return dao.findByTitle(title);
	}
	
	//@ResponseBody
	//public List<Thread> findByTag(){}
	
	@RequestMapping(value="/page/{page}", method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Thread> findByPage(@PathVariable int page){
		return dao.findByPage(page);
	}
}
