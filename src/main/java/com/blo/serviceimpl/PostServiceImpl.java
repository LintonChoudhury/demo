package com.blo.serviceimpl;


import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blo.entities.Category;
import com.blo.entities.Comment;
import com.blo.entities.Post;
import com.blo.entities.User;
import com.blo.exception.ResourceNotFoundException;
import com.blo.repositories.CategoryRepo;
import com.blo.repositories.CommentRepo;
import com.blo.repositories.PostRepo;
import com.blo.repositories.UserRepo;
import com.blo.services.PostService;
import com.blo.userDto.CommentDto;
import com.blo.userDto.PostDto;
import com.blo.userDto.PostResponse;
import com.blo.userDto.PostWithCommentsDto;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class PostServiceImpl implements PostService{
	
	@Autowired
	private PostRepo postrepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private CommentRepo commentRepo;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","User id",userId));
		Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Categoryid", categoryId));
		 Post post = mapper.map(postDto, Post.class);
		 post.setImageName("default.png");
		 post.setAddedDate(new Date());
		 post.setUser(user);
		 post.setCategory(category);
		 post.setLikes(0);
		 
		 Post newPost = postrepo.save(post);
		return mapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer id) {
		Post post = postrepo.findById(id).orElseThrow(()->new ResourceNotFoundException("Post", "post is", id));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		Post updatePost = postrepo.save(post);
		return mapper.map(updatePost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = postrepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "postid", postId));
		postrepo.delete(post);
		
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
		
		Sort sort = null;
		if(sortDir.equalsIgnoreCase("asc")) {
			sort=Sort.by(sortBy).ascending();
		}else {
			sort=Sort.by(sortBy).descending();
		}
		
		Pageable p = PageRequest.of(pageNumber, pageSize,sort);
		
	
		Page<Post> pagePost = postrepo.findAll(p);
		List<Post> allpost = pagePost.getContent();
		List<PostDto> postDto = allpost.stream().map(post->mapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDto);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		return postResponse;
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		Category cat = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("category", "caregory id", categoryId));
		List<Post> posts = postrepo.findByCategory(cat);
		 List<PostDto> postDto = posts.stream().map(post-> mapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDto;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "User Id", userId));
		List<Post> posts = postrepo.findByUser(user);
		List<PostDto> postDtos = posts.stream().map(post-> mapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post = postrepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "postid", postId));
		
		return mapper.map(post, PostDto.class);
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> posts = postrepo.findByTitleContaining(keyword);
		
//		List<Post> posts = postrepo.searchByTitle(null)
		List<PostDto> postDtos = posts.stream().map((post)->mapper.map(post, PostDto.class)).collect(Collectors.toList());
				
		return postDtos;
	}

	@Override
	public PostDto likePost(Integer postId) {
		Optional<Post> postOptional = postrepo.findById(postId);
		if (postOptional.isPresent()){
			Post post = postOptional.get();
			post.setLikes(post.getLikes()+1);
			Post save = postrepo.save(post);
			return mapper.map(save, PostDto.class);
	 
	}else {
		throw new RuntimeException("Post Not Found");
		
	}
}
	

//	private PostDto convertToDto(Post post) {
//        return new PostDto(post.getPostId(), post.getContent(), post.getLikes());
//    }

	@Override
	public PostWithCommentsDto getPostWithComments(Integer postId) {
		Optional<Post> postOptional = postrepo.findById(postId);
		if(postOptional.isPresent()) {
			Post post = postOptional.get();
			  List<Comment> comments=  commentRepo.findByPostPostId(postId);
			  List<CommentDto> commentDtos = comments.stream().map((comment)-> mapper.map(comment, CommentDto.class)).collect(Collectors.toList());
			  return new PostWithCommentsDto(
					  post.getPostId()
					,post.getTitle(),post.getContent(),post.getImageName(),post.getAddedDate(),post.getLikes(),comments.size(),commentDtos);
			
		}else {
			throw new RuntimeException("Post not found");
			}
		}
		private CommentDto convertToDto(Comment comment) {
	        return new CommentDto();
	}

		@Override
		public PostDto disLikePost(Integer postId) {
			// TODO Auto-generated method stub
			return null;
		}
}
