package com.imgscoop.services;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.imgscoop.dao.UserDAO;
import com.imgscoop.scoops.Role;
import com.imgscoop.scoops.User;

@Service
public class UserService {
	
	@Autowired
	private UserDAO dao;
	
	public void setDao(UserDAO dao){
		this.dao = dao;
	}
	
	//TODO: Password Encryption
	public ResponseEntity<User> create(User user) {
		try {
			dao.create(user);
			return new ResponseEntity<User>(user, HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<User>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	//TODO: Authorization. The user logged in has to be an Admin in order to delete
	public ResponseEntity<Void> delete(User user) {
		try {
			dao.delete(user);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT); 
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

	//TODO: Authorization. The updated user *MUST* be the user thats signed in
	public ResponseEntity<User> update(User user) {
		try {
			dao.update(user);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<List<User>> findByAll() {
		try {
			List<User> users = dao.findByAll();
			return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<User>>(HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<User> findByUsername(String username) {
		try {
			User user = dao.findByUsername(username);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
	}

	//TODO: Implement
	public ResponseEntity<List<User>> findByRole(Role role) {
		throw new UnsupportedOperationException("UserService.findByRole not implemented");
	}

	//TODO: Implement the password checking (dependent on the User bean having the password encryption
	public ResponseEntity<User> login(HttpServletRequest req, HttpServletResponse resp) {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		System.out.println(username + password);
		/** Option 1:
		 * 	-- Check to see if the either of the parameters are null or empty
		 * 	|---- If one or both are, then immediately return a bad request 
		 */
		ResponseEntity<User> response = this.findByUsername(username);
		/** Option 2:
		 * 	-- Check the status code of the above response
		 * 	|---- If it's a OK, then the user was found and we can continue the code
		 * 	|---- If it's a NOT_FOUND, then return a BAD_REQUEST
		 */
		User user = response.getBody();
		if(user.getPassword().equals(password)){
			user.setPassword(null);
			req.getSession().setAttribute("loggedin", user);
			try {
				resp.sendRedirect("index.html");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} else {
			req.getSession().setAttribute("loggedin", false);
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
	}

}
