package com.blo.repositories;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.blo.entities.Category;
import com.blo.entities.Post;
import com.blo.entities.User;

public interface PostRepo extends JpaRepository<Post, Integer>{

	List<Post> findByUser(User user);
	
	List<Post> findByCategory(Category category);
	
	List<Post> findByTitleContaining(String title);

//	List<Post> findByTitleContaining(Integer postId);
	
//	@Query ("select p from Post p where p.title like:key") 
//	List<Post> searchByTitle(@Param("key") String title);


}
