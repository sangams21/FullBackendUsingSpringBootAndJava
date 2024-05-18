package com.blogApplication.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogApplication.PayLoads.ApiResponse;
import com.blogApplication.PayLoads.CategoryDto;
import com.blogApplication.Services.Impl.CategoryServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
	private CategoryServiceImpl categorySer;
	
	//create 
	
	@PostMapping("/createCategory")
	public  ResponseEntity<CategoryDto>  createCategory(@Valid @RequestBody CategoryDto  categoryDto){
 		CategoryDto createdCat=this.categorySer.createCategory(categoryDto);
		return new ResponseEntity<>(createdCat,HttpStatus.CREATED);
	}
	
	// update 
	@PutMapping("/updateCategory/{cId}")
	public  ResponseEntity<CategoryDto>  updateCategory(@Valid @RequestBody CategoryDto categoryDto ,@PathVariable int cId){
 		CategoryDto updatedCat=this.categorySer.updateCategory(categoryDto,cId);
		return new ResponseEntity<>(updatedCat,HttpStatus.OK);
	}
	
	// delete 
	@DeleteMapping("/deleted/{cId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable int cId){
              this.categorySer.deleteCategory(cId);
			return new ResponseEntity<ApiResponse>( new ApiResponse("deleted successfully ", true),HttpStatus.OK);
	}
	
	
	
	
	// get
	@GetMapping("/get/{cId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable int cId){
            CategoryDto get=  this.categorySer.getCategory(cId);
			return new ResponseEntity<CategoryDto>(get,HttpStatus.OK);
	}
	
	
	@GetMapping("/getAll")
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
            List<CategoryDto> update=  this.categorySer.getAllCategory();
			return new ResponseEntity<List<CategoryDto>>(update,HttpStatus.OK);
	}

	
		
	
	
	

	
	

}
