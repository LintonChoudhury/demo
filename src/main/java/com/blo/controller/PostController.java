package com.blo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blo.services.PostService;
import com.blo.userDto.PostDto;
import com.blo.userDto.PostResponse;
import com.blo.userDto.PostWithCommentsDto;

@RestController
@RequestMapping("/api/")
public class PostController {
	
	@Autowired
	private PostService postService;

	@PostMapping("/user/{userId}/category/{categoryId}/post")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId){
		PostDto createPost = postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
	}
	


	//get  by user
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(
			@PathVariable Integer userId){
		List<PostDto> posts = postService.getPostsByUser(userId);
		
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
		
	}
	
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(
			@PathVariable Integer categoryId){
		List<PostDto> posts = postService.getPostsByCategory(categoryId);
		
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
		
	}
	//getAllpost
	@GetMapping("/allpost")
	public ResponseEntity<PostResponse> getAllPOst(
			@RequestParam (value="pageNumber",defaultValue = "0",required = false)Integer pageNumber,
			@RequestParam (value ="pageSize",defaultValue = "5",required = false)Integer pageSize,
			@RequestParam(value = "sortBy",defaultValue = "postId",required = false)String sortBy,
			@RequestParam(value = "sortDir",defaultValue = "asc",required = false)String sortDir){
		PostResponse postResponse = postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
		return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
	}
	
	@GetMapping("/po/{postId}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
		PostDto postDto = postService.getPostById(postId);
		return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
	}
	
	@PutMapping("/po/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody  PostDto postDto,  @PathVariable Integer postId){
		PostDto updatePost = postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
	
	//search 
	@GetMapping("post/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(
		@PathVariable("keyword")String keyword){
		List<PostDto> result = postService.searchPosts(keyword);
		return new ResponseEntity<List<PostDto>>(result,HttpStatus.OK);
	}
	@PostMapping("/{postId}/like")
	public PostDto likePost(@PathVariable Integer postId){
		return postService.likePost(postId);
	}
	
	public PostDto disLikePost(@PathVariable Integer postId) {
		return null;
	}
	
	@GetMapping("/{postId}/comments")
    public PostWithCommentsDto getPostWithComments(@PathVariable Integer postId) {
        return postService.getPostWithComments(postId);
    }
	

}
























