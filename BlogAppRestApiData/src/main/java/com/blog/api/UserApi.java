package com.blog.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.exception.BlogException;
import com.blog.model.Post;
import com.blog.model.UserEntity;
import com.blog.service.UserServiceImpl;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserApi {
	
	@Autowired
	private UserServiceImpl userService;
	
	@PostMapping
	public ResponseEntity<UserEntity> createUser(
			@Valid @RequestBody UserEntity user, BindingResult result) 
					throws BlogException{
		ResponseEntity<UserEntity> response= null;
		if(result.hasErrors()) {
			StringBuilder errMsg= new StringBuilder();
			for(FieldError err : result.getFieldErrors()) {
				errMsg.append(err.getDefaultMessage()+",");
			}
			throw new BlogException(errMsg.toString());
		}
		else {
			userService.add(user);
			response= new ResponseEntity<>(user,HttpStatus.OK);
		}
		
		return response;
	}
	
	@PutMapping
	public ResponseEntity<UserEntity> updateUser(@Valid @RequestBody UserEntity user,
			BindingResult result) throws BlogException{
	
		ResponseEntity <UserEntity> response = null;
		
		if(result.hasErrors()) {
			StringBuilder err = new StringBuilder();
			for(FieldError e: result.getFieldErrors()) {
				err.append(e.getDefaultMessage()+",");
			}throw new BlogException(err.toString());
		}
		
		userService.updateUser(user);
		response = new ResponseEntity<>(user,HttpStatus.OK);
	
		return response;
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deactivateById(
			@PathVariable("id") String id) throws BlogException{
		
		userService.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
