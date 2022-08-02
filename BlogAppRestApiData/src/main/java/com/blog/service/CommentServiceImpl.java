package com.blog.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.exception.BlogException;
import com.blog.model.Comment;
import com.blog.repository.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository cmntRepo;
	
	Logger logger =  LoggerFactory.getLogger(getClass());
	
	@Override
	public Comment addComment(Comment comment) throws BlogException {
		if (comment != null) {
			if(cmntRepo.existsById(comment.getCid())) {
				logger.warn("Please add vallid Comment");
				throw new BlogException("Warning... Not Vallid!!!");
				
			}
			cmntRepo.save(comment);
		}
		return comment;
	}

	@Override
	public List<Comment> getAllComment() {
		
		return cmntRepo.findAll();
	}

	@Override
	public Comment findByName(String name) throws BlogException {
		return cmntRepo.findByName(name);
	}

	@Override
	public Comment findByEmail(String email) throws BlogException {
		return cmntRepo.findByEmail(email);
	}

	
	@Override
	public Comment update(Comment comment) throws BlogException {

		if (comment != null) {
			if(!cmntRepo.existsById(comment.getCid())) {
				logger.warn("No Such comment available to update"+comment.getCid());
				throw new BlogException("Warning... Not Vallid!!!");
			}
			cmntRepo.save(comment);
		}
		return comment;
	}
	

	@Override
	public boolean deleteById(Integer id) throws BlogException {
		boolean deleted = false;
		
		if(!cmntRepo.existsById(id)) {
			logger.warn("No Such comment available to delete"+id);
			throw new BlogException("Warning.. No such Post Deleted!!");
		}
		cmntRepo.deleteById(id);
		return deleted;
	}

//	@Override
//	public Comment findByString(String str) throws BlogException {
//		return cmntRepo.findByString(str);
//	}

}
