package com.blo.services;

import java.util.List;

import com.blo.userDto.PostDto;
import com.blo.userDto.PostResponse;
import com.blo.userDto.PostWithCommentsDto;

public interface PostService {

	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	
	PostDto updatePost(PostDto postDto,Integer id);
	
	void deletePost(Integer postId);
	
	PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	
	PostDto getPostById(Integer postId);
	
	List<PostDto> searchPosts(String keyword);
	
	List<PostDto> getPostsByCategory(Integer categoryId);
	
	List<PostDto> getPostsByUser(Integer userId);
	
	PostDto likePost(Integer postId);
	
	PostDto disLikePost(Integer postId);
	
	PostWithCommentsDto getPostWithComments(Integer postId);
}
