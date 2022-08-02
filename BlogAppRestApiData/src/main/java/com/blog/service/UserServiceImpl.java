package com.blog.service;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blog.exception.BlogException;
import com.blog.model.UserEntity;
import com.blog.repository.UserRepository;

@Service("UserService")
public class UserServiceImpl implements UserService,UserDetailsService {
	
	@Autowired
	private UserRepository userRepo;
	
	//@Autowired
	private PasswordEncoder passwordEncoder;
	
	public UserServiceImpl(UserRepository userRepo) {
		this.userRepo= userRepo;
		this.passwordEncoder = new BCryptPasswordEncoder();
	}

	@Transactional
	@Override
	public UserEntity add(UserEntity user) throws BlogException {
		if (user != null) {
			if(userRepo.existsById(user.getUserId())) {
				throw new BlogException("User Already Exsists!!");
			}
			userRepo.save(user);
		}
		return user;
	}


	@Override
	public UserEntity save(UserEntity user)
	{
	   	user.setEncodedPassword(passwordEncoder.encode(user.getPassword()));
    	return userRepo.saveAndFlush(user);
    }
		
//	@Override
//	public UserEntity save(UserEntity user) {
//		user.setPassword(user.getPassword());
//		return userRepo.saveAndFlush(user);
//	}

	@Override
	public UserDetails loadUserByUsername(String userId) {
		if(!userRepo.existsById(userId)) {
			throw new UsernameNotFoundException("No such user exsists");
		}
		UserEntity user = userRepo.findById(userId).orElse(null);
		return new User(String.valueOf(user.getUserId()),user.getEncodedPassword(),new ArrayList<>());
	}

	@Override
	public UserDetails loadUserByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDetails loadUserByPhone(String phone) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserEntity updateUser(UserEntity user) throws BlogException {
		if (user != null) {
			if(!userRepo.existsById(user.getUserId())) {
				throw new BlogException("Warning... Not Vallid Post!!!");
			}
			userRepo.save(user);
		}
		return user;
	}

	@Override
	public boolean deleteById(String id) throws BlogException {
boolean deleted = false;
		
		if(!userRepo.existsById(id)) {
			throw new BlogException("Warning.. No such User Deleted!!");
		}
		userRepo.deleteById(id);
		return deleted;
	}

}
