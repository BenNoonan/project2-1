package com.imgscoop.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imgscoop.scoops.Thread;
import com.imgscoop.services.ThreadService;

@Controller
@RequestMapping(value="/thread")
public class ThreadController {

	@Autowired
	private ThreadService service;

	public void setDao(ThreadService service) {
		this.service = service;
	}

	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Thread> create(@Valid @RequestParam String title){
		return service.create(new Thread(null, title, null));
	}
	
	@RequestMapping(method=RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<Void> delete(@RequestParam int id){
		return service.delete(id);
	}
	
	@RequestMapping(method=RequestMethod.PUT,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void update(@RequestBody Thread thread){
		service.update(thread);
	}
	
	@RequestMapping(value="/all", method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Thread>> findByAll(){
		return service.findByAll();
	}
	
	@RequestMapping(value="/title", method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Thread>>  findByTitle(@RequestBody String title){
		return service.findByTitle(title);
	}
	
	//@ResponseBody
	//public List<Thread> findByTag(){}
	
	@RequestMapping(value="/page/{page}", method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Thread>> findByPage(@PathVariable int page){
		return service.findByPage(page);
	}
	
	@RequestMapping(value="/id={id}", method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Thread>> findById(@PathVariable int id){
		return service.findById(id);
	}
}