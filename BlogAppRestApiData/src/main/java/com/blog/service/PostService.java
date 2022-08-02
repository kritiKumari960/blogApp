package com.blog.service;

import java.util.List;

import com.blog.exception.BlogException;
import com.blog.model.Post;

public interface PostService {
	
	Post addPost(Post post) throws BlogException;
	
	List<Post> getAllPost();
	
	Post getByTitle(String title) throws BlogException;
		
	Post update(Post post) throws BlogException;
	
	boolean deleteById(Integer id) throws BlogException;

	Post getById(Integer id);

}
