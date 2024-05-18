package com.blogApplication.Services.Impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.blogApplication.Entity.Category;
import com.blogApplication.Entity.Post;
import com.blogApplication.Entity.User;
import com.blogApplication.Exception.ResourceNotFoundExeption;
import com.blogApplication.PayLoads.PostDto;
import com.blogApplication.PayLoads.PostResponse;
import com.blogApplication.Repository.CategoryRepositoy;
import com.blogApplication.Repository.PostRepository;
import com.blogApplication.Repository.UserRepository;
import com.blogApplication.Services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CategoryRepositoy  catRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	// for adding the post data
	@Override
	public PostDto createPost(PostDto postDto,int id,int cId) {
    User user=this.userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundExeption("user ", "id", id));
		
		Category cat=this.catRepo.findById(cId).orElseThrow(()->new ResourceNotFoundExeption("catagory", "cId", cId));
		Post post=this.modelMapper.map(postDto, Post.class);		
		post.setImg("default.png");
		post.setEditDate(new Date());
		post.setUser(user);
		post.setCategory(cat);
		Post added=this.postRepo.save(post);
		return this.modelMapper.map(added,PostDto.class);
	}

	
	//  for updating the post data
	@Override
	public PostDto updatePost(PostDto postDto, int postId) {

		Post post=  this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundExeption("PostId", "postId", postId));
		
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImg(postDto.getImg());
		
		Post updated=this.postRepo.save(post);
		return this.modelMapper.map(updated, PostDto.class);
	}

	// for deleting the post data
	@Override
	public void deletePost(int postId) {
      Post post=  this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundExeption("PostId", "postId", postId));
      
      this.postRepo.delete(post);
		
	}

	// for read or get the all post
	@Override
	public PostResponse getAllPost(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
	
		Sort sort=(sortDir.equalsIgnoreCase("asc"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
		
		Pageable p=PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> pagePost=this.postRepo.findAll(p);
		
	    List<Post> allPost=	pagePost.getContent();
        List<PostDto> postDto=allPost.stream().map(post->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        
        PostResponse postResponse=new PostResponse();
        postResponse.setContent(postDto);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElement(pagePost.getTotalElements());
        postResponse.setTotalPage(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());		
		
		return  postResponse;
	}


    // for get the post by id
	@Override
	public PostDto getpostById(int postId) {
		Post get= this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundExeption("postid", "postid", postId));
		return this.modelMapper.map(get, PostDto.class);
	}

	@Override
	public List<PostDto> getPostByCataegory(int cId) {
		Category cat=this.catRepo.findById(cId).orElseThrow(()-> new ResourceNotFoundExeption("category", "cId", cId));
	List<Post> posts=	this.postRepo.findByCategory(cat);
	
	List<PostDto> postDto=posts.stream().map((post) ->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDto;
	}

	@Override
	public List<PostDto> getPostByUser(int id) {
         User user=this.userRepo.findById(id).orElseThrow(()->new ResourceNotFoundExeption("User", "id", id));
   List<Post> posts=    this.postRepo.findByUser(user);  
   System.out.print(posts);
   List<PostDto> postDto=  posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
   System.out.println(postDto);
		return postDto;
	}

	@Override
	public List<PostDto> seacrhPost(String keyword) {

		List<Post> posts = this.postRepo.searchByTitle("%"+keyword+"%");
		
		List<PostDto> postDtos = posts.stream().map((post)-> this.modelMapper.map(posts, PostDto.class)).collect(Collectors.toList());
		
		return postDtos;
	}



}
