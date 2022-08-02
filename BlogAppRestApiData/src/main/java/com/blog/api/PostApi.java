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
import com.blog.model.Post;
import com.blog.service.PostService;

@RestController
@RequestMapping("/post")
public class PostApi {
	
	@Autowired
	private PostService postService;
	
	Logger logger =  LoggerFactory.getLogger(getClass());
	
	@GetMapping
	public ResponseEntity<List<Post>> postList() throws BlogException{
		logger.info("Posts retrived");
		return new ResponseEntity<>(postService.getAllPost(),HttpStatus.OK);
	}
	
	@GetMapping("/{id:[0-9]{1,5}}")
	public ResponseEntity<Post> findByID(@PathVariable("id") Integer id) throws BlogException{
		ResponseEntity <Post> response = null;
		
		Post post = postService.getById(id);
		if(post == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			logger.warn("Post ID Not available for search: "+id);
		}
		else {
			response = new ResponseEntity<>(post, HttpStatus.OK);
			logger.info("Post retrived");
			
		}
		return response;
	}
	
	@GetMapping("/{title:[A-Za-z]{5,25}}")
	public ResponseEntity<Post> findByTitle(@PathVariable("title") String title) throws BlogException{
		ResponseEntity <Post> response = null;
		
		Post post = postService.getByTitle(title);
		if(post == null) {
			response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			logger.warn("Post title Not available for search: "+title);
		}
		else {
			response = new ResponseEntity<>(post, HttpStatus.OK);
			logger.info("Post retrived");
		}
		return response;
	}

	@PostMapping
	public ResponseEntity<Post> createPost(
			@Valid @RequestBody Post post, BindingResult result) throws BlogException{
		
		ResponseEntity <Post> response = null;
		if(result.hasErrors()) {
			StringBuilder err = new StringBuilder();
			for(FieldError e : result.getFieldErrors()) {
				err.append(e.getDefaultMessage()+",");
			}
			 new BlogException(err.toString());
			 
		}else {
			postService.addPost(post);
			response = new ResponseEntity<>(post,HttpStatus.OK);
			logger.info("New Post Added");
		}
				
		return response;
	}
	
	

	@PutMapping()
	public ResponseEntity<Post> updateProduct(@Valid @RequestBody Post post,
			BindingResult result) throws BlogException{
	
		ResponseEntity <Post> response = null;
		
		if(result.hasErrors()) {
			StringBuilder err = new StringBuilder();
			for(FieldError e: result.getFieldErrors()) {
				err.append(e.getDefaultMessage()+",");
			}throw new BlogException(err.toString());
		}
		
		postService.update(post);
		response = new ResponseEntity<>(post,HttpStatus.OK);
		logger.info("Post Updated");
	
		return response;
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePostById(
			@PathVariable("id") Integer id) throws BlogException{
		
		postService.deleteById(id);
		logger.info("Post Deleted");
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
