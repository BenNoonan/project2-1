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

import com.imgscoop.dao.UserDAO;
import com.imgscoop.scoops.Role;
import com.imgscoop.scoops.User;

@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private UserDAO dao;
	
	public void setDao(UserDAO dao) {
		this.dao = dao;
	}

	@RequestMapping(method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void create(@Valid @RequestBody User user){
		dao.create(user);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void delete(@RequestBody User user){
		dao.delete(user);
	}
	@RequestMapping(method=RequestMethod.PUT,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void update(User user){
		dao.update(user);
	}
	
	@RequestMapping(value="/all", method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<User> findByAll(){
		return dao.findByAll();
	}
	
	@RequestMapping(value="/username", method=RequestMethod.DELETE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public User findByUsername(String username){
		return dao.findByUsername(username);
	}
	
	@RequestMapping(value="/role", method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<User> findByRole(Role role){
		return dao.findByRole(role);
	}

}
