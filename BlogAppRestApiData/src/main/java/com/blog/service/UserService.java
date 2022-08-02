package com.blog.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.blog.exception.BlogException;
import com.blog.model.UserEntity;

@Service
public interface UserService {
	
	UserEntity add(UserEntity user) throws BlogException;
	public UserEntity updateUser(UserEntity user) throws BlogException;
	boolean deleteById(String id) throws BlogException;
	public UserEntity save(UserEntity user);
	public UserDetails loadUserByUsername(String userId);
	public UserDetails loadUserByEmail(String email);
	public UserDetails loadUserByPhone(String phone);
	
	

}
