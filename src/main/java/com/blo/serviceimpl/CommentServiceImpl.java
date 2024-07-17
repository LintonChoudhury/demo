package com.blo.serviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blo.entities.Comment;
import com.blo.entities.Post;
import com.blo.exception.ResourceNotFoundException;
import com.blo.repositories.CommentRepo;
import com.blo.repositories.PostRepo;
import com.blo.services.CommentService;
import com.blo.userDto.CommentDto;
@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		
		Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post id", postId));
		
		Comment comment = mapper.map(commentDto,Comment.class);
		
		comment.setPost(post);
		
		Comment savedComment = commentRepo.save(comment);
		
		return mapper.map(savedComment, CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment comme = commentRepo.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "comment Id", commentId));
		commentRepo.delete(comme);
	}

}
