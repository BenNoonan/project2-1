package com.imgscoop.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.imgscoop.dao.ThreadDAO;
import com.imgscoop.scoops.Thread;

@Service
public class ThreadService {
	
	@Autowired
	private ThreadDAO dao;

	public void setDao(ThreadDAO dao) {
		this.dao = dao;
	}
	
	public ResponseEntity<Thread> create(Thread thread){
		try{
			dao.create(thread);
			return new ResponseEntity<Thread>(thread, HttpStatus.CREATED);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Thread>(HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<Thread> update(Thread thread){
		try {
			dao.update(thread);
			return new ResponseEntity<Thread>(thread, HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<Thread>(HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<Void> delete(Thread thread){
		try{
			dao.delete(thread);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<List<Thread>> findByAll(){
		try{
			return new ResponseEntity<List<Thread>>(dao.findByAll(), HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Thread>>(HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<List<Thread>> findByTitle(String title){
		try{
			return new ResponseEntity<List<Thread>>(dao.findByTitle(title), HttpStatus.OK);	
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Thread>>(HttpStatus.NOT_FOUND);
		}
	}
	
	//@ResponseBody
	//public List<Thread> findByTag(){}

	public ResponseEntity<List<Thread>> findByPage(int page){
		try{		
			return new ResponseEntity<List<Thread>>(dao.findByPage(page), HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Thread>>(HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<List<Thread>> findById(int id){
		try{		
			return new ResponseEntity<List<Thread>>(dao.findById(id), HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<Thread>>(HttpStatus.NOT_FOUND);
		}
	}
}
