package com.blogApplication.Services.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogApplication.Entity.Comment;
import com.blogApplication.Entity.Post;
import com.blogApplication.Exception.ResourceNotFoundExeption;
import com.blogApplication.PayLoads.CommentDto;
import com.blogApplication.Repository.CoomenRespo;
import com.blogApplication.Repository.PostRepository;
import com.blogApplication.Services.CommentService;


@Service
public class CommentServiceImpl  implements CommentService{

	@Autowired
	private CoomenRespo commentRepo;
	
	@Autowired
	private PostRepository postRepo;
	
	@Autowired
	private ModelMapper moddelMapper;
	
	@Override
	public CommentDto createCommentDto(CommentDto commentDTo, int postId) {
           Post post= this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundExeption("postId", "postId", postId));
           Comment comment=this.moddelMapper.map(commentDTo, Comment.class);
           
           comment.setPost(post);
           
           Comment saveComment = this.commentRepo.save(comment);
           
           
           
           
		return this.moddelMapper.map(saveComment, CommentDto.class);
	}

	@Override
	public void deleteComment(int commentId) {

		Comment comment=this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundExeption("Comment", "Commet", commentId));		
		this.commentRepo.delete(comment);
	}

}
