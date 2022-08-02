package com.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.model.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, String>{

}
