package com.blogApplication.Services.Impl;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogApplication.Entity.Category;
import com.blogApplication.Exception.ResourceNotFoundExeption;
import com.blogApplication.PayLoads.CategoryDto;
import com.blogApplication.Repository.CategoryRepositoy;
import com.blogApplication.Services.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepositoy categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	// add the category
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
	Category cat=	this.modelMapper.map(categoryDto, Category.class);
	Category added=this.categoryRepo.save(cat);
	return this.modelMapper.map(added, CategoryDto.class);
	}

	@Override
	// update the category
	public CategoryDto updateCategory(CategoryDto categoryDto, int cId) {
		
		Category cat=this.categoryRepo.findById(cId).orElseThrow(()-> new ResourceNotFoundExeption("category", "cId", cId));
		cat.setCTitle(categoryDto.getCTitle());
		cat.setCDiscription(categoryDto.getCDiscription());		
		Category updatedCategory=this.categoryRepo.save(cat);
		return this.modelMapper.map(updatedCategory, CategoryDto.class);
	}
	

	@Override
	public void deleteCategory(int cId) {
		Category cat=this.categoryRepo.findById(cId).orElseThrow(()-> new ResourceNotFoundExeption("category","cId",cId));
		this.categoryRepo.delete(cat);
		
	}

	@Override
	public CategoryDto getCategory(int cId) {
		Category cat=this.categoryRepo.findById(cId).orElseThrow(()-> new ResourceNotFoundExeption("category","cId",cId));
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {

		List<Category> cat=this.categoryRepo.findAll();
		
		List<CategoryDto> catdtos=	cat.stream().map(c->this.modelMapper.map(c, CategoryDto.class)).collect(Collectors.toList());
		return catdtos;
	}

	
}
