package com.blog.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.blog.helper.JwtUtil;
import com.blog.model.JwtRequest;
import com.blog.model.JwtResponse;
import com.blog.model.UserEntity;
import com.blog.service.UserDetailServiceImpl;
import com.blog.service.UserServiceImpl;

@RestController
@CrossOrigin
public class JwtController {
	
//	@Autowired
//	private UserDetailServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserServiceImpl userService;
	
//	@RequestMapping(value="/token",method=RequestMethod.POST)
//	public ResponseEntity<?> generateToken( @RequestBody JwtRequest jwtRequest)throws Exception{
//		System.out.println(jwtRequest);
//		try {
//			authenticate(jwtRequest.getUsername(),jwtRequest.getPassword());	
//		}catch(UsernameNotFoundException e) {
//			e.printStackTrace();
//			throw new Exception("User not Found");
//		}	
//	UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(jwtRequest.getUsername());
//		
//	String token=this.jwtUtil.generateToken(userDetails);
//	return ResponseEntity.ok(new JwtResponse(token));
//		
//	}

	
	@PostMapping(value= "/signin")
	public ResponseEntity<JwtResponse> generateToken(@RequestBody UserEntity user) throws Exception {
		try {
			authenticate(user.getUserId(), user.getPassword());			
		}
		catch(UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("User not Found");
		}
		UserDetails userDetails = this.userService.loadUserByUsername(user.getUserId());
		String token = jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	private void authenticate(String username,String password) throws Exception {
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		}catch(DisabledException e) {
			throw new Exception("User Disabled "+e.getMessage());
		}catch(BadCredentialsException e) {
			throw new Exception("Invalid Credentials "+e.getMessage());
		}
		
	}
	
	@PostMapping("/signup")
	public ResponseEntity<UserEntity> saveUser(@RequestBody UserEntity user) throws Exception {
		return ResponseEntity.ok(userService.save(user));
	}

}
