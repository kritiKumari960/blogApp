package com.blog.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.blog.helper.JwtUtil;
import com.blog.service.UserDetailServiceImpl;
import com.blog.service.UserServiceImpl;



@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserServiceImpl  userDetailsServiceImpl;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String requestTokenHeader= request.getHeader("Authorization");
		String username=null;
		String jwtToken=null;
		if(requestTokenHeader!=null&& requestTokenHeader.startsWith("Bearer "))
		{
			jwtToken=requestTokenHeader.substring(7);
			try {
			username =	this.jwtUtil.extractUsername(jwtToken);
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			UserDetails userDetails=this.userDetailsServiceImpl.loadUserByUsername(username);
			if(username!=null&& SecurityContextHolder.getContext().getAuthentication()==null) {
		    
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken   =		new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
			
		         usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			
			     SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				
			}else {
				System.out.println("Token is not validated");
			}
			
		}
		filterChain.doFilter(request, response);

		
	}

}
