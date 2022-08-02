package com.blog.api;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.exception.BlogException;
import com.blog.model.Comment;
import com.blog.service.CommentService;

@RestController
@RequestMapping("/comment")
public class CommentApi {
	
	@Autowired
	private CommentService cmntService;
	
	Logger logger =  LoggerFactory.getLogger(getClass());
	
	@GetMapping
	public ResponseEntity<List<Comment>> cmntList() throws BlogException{
		logger.info("Comments retrived");
		return new ResponseEntity<>(cmntService.getAllComment(),HttpStatus.OK);
	}
	
	@GetMapping("/searchName/{name}")
	public ResponseEntity<Comment> findByName(@PathVariable("name") String name) throws BlogException{
		ResponseEntity <Comment> response = null;
		
		Comment cmnt = cmntService.findByName(name);
		if(cmnt == null) {
			logger.warn("Comment with name: " +name+" is not available.");
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else {
			logger.info("Comments retrived");
			response = new ResponseEntity<>(cmnt, HttpStatus.OK);
		}
		return response;
	}
	

	@GetMapping("/searchEmail/{email}")
	public ResponseEntity<Comment> findByEmail(@PathVariable("email") String email) throws BlogException{
		ResponseEntity <Comment> response = null;
		
		Comment cmnt = cmntService.findByEmail(email);
		if(cmnt == null) {
			logger.warn("Comment with name: " +email+" is not available.");
			
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		else {
			response = new ResponseEntity<>(cmnt, HttpStatus.OK);
			logger.info("Comments retrived");
		}
		return response;
	}
	
//	@GetMapping("/{str}")
//	public ResponseEntity<Comment> findByName(@PathVariable("str") String str) throws BlogException{
//		ResponseEntity <Comment> response = null;
//		
//		Comment cmnt = cmntService.findByString(str);
//		if(cmnt == null) {
//			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//		response = new ResponseEntity<>(cmnt, HttpStatus.OK);
//		return response;
//	}
	
	@PostMapping
	public ResponseEntity<Comment> createPost(
			@Valid @RequestBody Comment comment, BindingResult result) throws BlogException
	{
		
		ResponseEntity <Comment> response = null;
		if(result.hasErrors()) {
			StringBuilder err = new StringBuilder();
			for(FieldError e : result.getFieldErrors()) {
				err.append(e.getDefaultMessage()+",");
			}
			throw new BlogException(err.toString());
		}else {
			cmntService.addComment(comment);
			response = new ResponseEntity<>(comment,HttpStatus.OK);
			logger.info("Comment Added");
		}
				
		return response;
	}
	
	@PutMapping
	public ResponseEntity<Comment> updateProduct(@Valid @RequestBody Comment comment,
			BindingResult result) throws BlogException{
	
		ResponseEntity <Comment> response = null;
		
		if(result.hasErrors()) {
			StringBuilder err = new StringBuilder();
			for(FieldError e: result.getFieldErrors()) {
				err.append(e.getDefaultMessage()+",");
			}throw new BlogException(err.toString());
		}
		else {
			cmntService.update(comment);
			response = new ResponseEntity<>(comment,HttpStatus.OK);
			logger.info("Comment Updated");
		}
	
		return response;
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCommentById(
			@PathVariable("id") Integer id) throws BlogException{
		
		cmntService.deleteById(id);
		logger.info("Comment Deleted");
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
