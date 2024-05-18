package com.blogApplication.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogApplication.Entity.Comment;
import com.blogApplication.PayLoads.ApiResponse;
import com.blogApplication.PayLoads.CommentDto;
import com.blogApplication.Services.CommentService;

@RestController
@RequestMapping("/commentApi")
public class CommentController {

	@Autowired
	private CommentService commentServ;
	
	@PostMapping("/ceratedComment/{postId}")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto Comment , @PathVariable int postId){
		
		CommentDto createCommentDto = this.commentServ.createCommentDto(Comment, postId);
		return new ResponseEntity<CommentDto>(createCommentDto,HttpStatus.OK);
	}
	
	@DeleteMapping("/deletedComment/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable("commentId") int commentId){		
		 this.commentServ.deleteComment(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("deleted succesfully" ,true),HttpStatus.OK);
	}
	
}
