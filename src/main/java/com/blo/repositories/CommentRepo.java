package com.blo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blo.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer>{

	List<Comment> findByPostPostId(Integer postId);
	
//	List<Comment> findByPost(Integer post);

}
