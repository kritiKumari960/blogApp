package com.blog.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//System.out.println("Username = "+username);
		String name="kriti";
		if(username.equals(name)) {
			return new User("kriti","kumari",new ArrayList<>());
		}else {
			throw new UsernameNotFoundException("User not found...");
		}
	}

}
