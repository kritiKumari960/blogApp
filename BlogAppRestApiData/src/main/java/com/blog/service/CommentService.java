package com.blog.service;

import java.util.List;

import com.blog.exception.BlogException;
import com.blog.model.Comment;

public interface CommentService {

	Comment addComment(Comment comment) throws BlogException;
	
	List<Comment> getAllComment();
	
//	Comment getByName(String name) throws BlogException;
	
//	Comment getByEmail(String email) throws BlogException;
	
	Comment update(Comment comment) throws BlogException;
	
	boolean deleteById(Integer id) throws BlogException;

	Comment findByName(String name) throws BlogException;

	Comment findByEmail(String email) throws BlogException;
	
//	Comment findByString(String str) throws BlogException;
}
