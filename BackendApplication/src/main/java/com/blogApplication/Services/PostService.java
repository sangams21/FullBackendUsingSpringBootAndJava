package com.blogApplication.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blogApplication.Entity.Post;
import com.blogApplication.PayLoads.PostDto;
import com.blogApplication.PayLoads.PostResponse;

@Service
public interface PostService {
	
	
	// cfreate a post or add the post
	PostDto createPost(PostDto postdto,int id,int cId);
	
	//update the post or put the post 
	PostDto updatePost(PostDto postDto ,int postId);

	
	// delete the post
	void deletePost(int postId);
	
	
	// get all the post 
	PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	

	
	// get the data based on post id
	PostDto getpostById(int postId);
	
	
	//get  the poesed based on category
	List<PostDto>  getPostByCataegory(int cId);
	
	//get all post by user 
	List<PostDto> getPostByUser(int id);
	
	
	

	// get seacrh psot
	List<PostDto>  seacrhPost(String keyword);
}
