package com.blog.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blog.exception.BlogException;


@RestControllerAdvice
public class BlogExceptionAdvice {
	
	@ExceptionHandler(BlogException.class)
	public ResponseEntity<String> handleItemException(BlogException exception){
		return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
	}

}
