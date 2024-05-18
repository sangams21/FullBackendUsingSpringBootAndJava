package com.blogApplication.Controller;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.StreamUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.blogApplication.Config.AppConstant;
import com.blogApplication.Entity.Post;
import com.blogApplication.PayLoads.ApiResponse;
import com.blogApplication.PayLoads.PostDto;
import com.blogApplication.PayLoads.PostResponse;
import com.blogApplication.Services.FileService;
import com.blogApplication.Services.PostService;
import com.blogApplication.Services.Impl.PostServiceImpl;



import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService postServi;
	
	@Autowired
	private FileService fileServ;
	
	@Value("${project.image}")
	private String path;
	@PostMapping("/user/{id}/category/{cId}/post")
	public ResponseEntity<PostDto> createdPost(
			@RequestBody PostDto postdto,
			@PathVariable int id,
			@PathVariable int cId){
		
		PostDto createdpost=this.postServi.createPost(postdto, id, cId);
		
		return new ResponseEntity<PostDto>(createdpost,HttpStatus.CREATED);
	}
	
	// get by user
	@GetMapping("getByUser/{id}/post")
	public ResponseEntity<List<PostDto>>  getByUser(@PathVariable int id){
		List<PostDto> getdata=this.postServi.getPostByUser(id);
		System.out.print(getdata);
		return new ResponseEntity<List<PostDto>>(getdata,HttpStatus.FOUND);	
	}
	
	// get by category
	@GetMapping("getBycategory/{cId}/post")
	public ResponseEntity<List<PostDto>>  getByCategory(@PathVariable int cId){
		List<PostDto> getdataCategory=this.postServi.getPostByCataegory(cId);
		return new ResponseEntity<List<PostDto>>(getdataCategory,HttpStatus.OK);	
	}
	
	// get All Post
	@GetMapping("/getAllPost")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value="pageNumber" ,defaultValue = AppConstant.pageNumber ,required = false) Integer pageNumber,
			@RequestParam(value="pageSize" ,defaultValue = AppConstant.pageSize,required = false) Integer pageSize,
			@RequestParam(value="sortBy" ,defaultValue = AppConstant.sortBy,required = false) String sortBy,
			@RequestParam(value="sortDir" ,defaultValue = AppConstant.sortDir,required = false) String sortDir
			
			){
		PostResponse allPost = this.postServi.getAllPost(pageNumber,pageSize,sortBy,sortDir);	
		return new ResponseEntity<PostResponse>(allPost,HttpStatus.OK);
	}
	@GetMapping("/getAllPost/{postId}")
	public ResponseEntity<PostDto>  getPostById(@PathVariable int postId){
		PostDto getdata=this.postServi.getpostById(postId);
		return new ResponseEntity<PostDto>(getdata,HttpStatus.OK);
	}
	
	@DeleteMapping("deleted/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable int postId) {
	     this.postServi.deletePost(postId);
	     return new ResponseEntity<ApiResponse>(new ApiResponse("deleted succesfully" ,true),HttpStatus.OK);
	}
	
	@PutMapping("/updatePost/{postId}")
	public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto ,@PathVariable int postId) {
		
	PostDto updated=	this.postServi.updatePost(postDto, postId);
	
	return new ResponseEntity<PostDto>(updated,HttpStatus.OK);
		
	}
	
	@GetMapping("/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPost(@PathVariable String keyword){
		
		List<PostDto> seacrhPost = this.postServi.seacrhPost(keyword);
		
		return new ResponseEntity<List<PostDto>>(seacrhPost,HttpStatus.OK);
	}
	
	
	// post image upload
	
	@PostMapping("/uploadImg/{postId}")
	public ResponseEntity<PostDto> uploadPostImg(@RequestParam("image") MultipartFile image, @PathVariable int postId	) throws IOException{
		PostDto postDto = this.postServi.getpostById(postId);
	  String fileName = this.fileServ.uploadImg(path, image);		  
	postDto.setImg(fileName);
	PostDto updatePost = this.postServi.updatePost(postDto, postId);
	
	return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
		
	}
	
	@GetMapping(value="/checkImf/{imageName}" ,produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImg(@PathVariable("imageName") String imageName,HttpServletResponse response ) throws IOException {
		
		InputStream resource=this.fileServ.getResource(path, imageName);
		
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		
	  org.springframework.util.StreamUtils.copy(resource, response.getOutputStream());
		
	}
	
	
	
	
	
	
	
	
}
