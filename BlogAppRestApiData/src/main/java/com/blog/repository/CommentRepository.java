package com.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.blog.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer>{


	Comment findByName(String name);

	Comment findByEmail(String email);
	
//	@Query("SELECT i FROM Comment i WHERE i.name = str OR i.email = str")
//	Comment findByString(String str);
}
