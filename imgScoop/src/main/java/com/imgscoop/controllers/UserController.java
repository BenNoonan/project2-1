package com.imgscoop.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imgscoop.scoops.Role;
import com.imgscoop.scoops.User;
import com.imgscoop.services.UserService;

@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private UserService service;
	
	public void setDao(UserService service) {
		this.service = service;
	}
	
	@RequestMapping(method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<User> create(@Valid @RequestBody User user, HttpServletRequest req){
		System.out.println(user);
		return service.create(user);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Void> delete(@RequestBody User user){
		return service.delete(user);
	}
	@RequestMapping(method=RequestMethod.PUT,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<User> update(@Valid @RequestBody User user){
		return service.update(user);
	}
	
	@RequestMapping(value="/all", method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<User>> findByAll(){
		return service.findByAll();
	}
	
	@RequestMapping(value="/{username}", method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<User> findByUsername(@PathVariable String username){
		return service.findByUsername(username);
	}
	
	@RequestMapping(value="/role", method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<User>> findByRole(Role role){
		return service.findByRole(role);
	}

}
