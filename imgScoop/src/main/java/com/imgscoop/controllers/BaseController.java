package com.imgscoop.controllers;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imgscoop.scoops.User;
import com.imgscoop.services.UserService;

@Controller
public class BaseController {
	
	@Autowired
	private UserService service;

	public void setService(UserService service) {
		this.service = service;
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public void loadMain(HttpServletRequest req, HttpServletResponse resp){
		try {
			req.getRequestDispatcher("/index.html").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<User> login(HttpServletRequest req, HttpServletResponse resp){
		return service.login(req, resp);
	}
	
}
