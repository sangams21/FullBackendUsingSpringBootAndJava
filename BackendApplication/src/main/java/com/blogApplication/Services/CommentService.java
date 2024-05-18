package com.blogApplication.Services;

import com.blogApplication.PayLoads.CommentDto;

public interface CommentService {
	
	
	public CommentDto createCommentDto(CommentDto commentDTo,int postId);
	
	void deleteComment(int commentId);
	

}
