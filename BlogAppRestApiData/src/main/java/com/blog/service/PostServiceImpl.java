package com.blog.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.exception.BlogException;
import com.blog.model.Post;
import com.blog.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepository postRepo;
	
	Logger logger =  LoggerFactory.getLogger(getClass());

	
	@Transactional
	@Override
	public Post addPost(Post post) throws BlogException {
		if (post != null) {
			if(postRepo.existsById(post.getpId())) {
				logger.warn("Please add vallid Post");
				throw new BlogException("Warning... Not Vallid Post!!!");
			}
			postRepo.save(post);
		}
		return post;
	}

	@Override
	public List<Post> getAllPost() {
		return postRepo.findAll();
	}

	@Override
	public Post getByTitle(String title) throws BlogException {
		return postRepo.findByTitle(title);
	}


	@Transactional
	@Override
	public boolean deleteById(Integer id) throws BlogException {
		boolean deleted = false;
		
		if(!postRepo.existsById(id)) {
			logger.warn("No such id available to delete: "+id);
			throw new BlogException("Warning.. No such Post Deleted!!");
		}
		postRepo.deleteById(id);
		return deleted;
	}

	@Transactional
	@Override
	public Post update(Post post) throws BlogException {
	
		if (post != null) {
			if(!postRepo.existsById(post.getpId())) {
				logger.warn("No such id available to Update: "+post.getpId());
				throw new BlogException("Warning... Not Vallid Post!!!");
			}
			postRepo.save(post);
		}
		return post;
	}

	@Override
	public Post getById(Integer id) {
		
		return postRepo.findById(id).orElse(null);
	}
	
}
