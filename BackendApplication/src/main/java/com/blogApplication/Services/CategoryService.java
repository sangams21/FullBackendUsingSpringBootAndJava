package com.blogApplication.Services;

import java.util.List;

import com.blogApplication.PayLoads.CategoryDto;

public interface CategoryService {

	// for create 
	public CategoryDto createCategory(CategoryDto categoryDto);
	
	//update 
	public CategoryDto updateCategory(CategoryDto categoryDto,int cId);
	
	//delete 
	
	public void deleteCategory(int cId);
	
	// read 
	public CategoryDto getCategory(int cId);
	
	List<CategoryDto> getAllCategory();
	
	
	
	
}
