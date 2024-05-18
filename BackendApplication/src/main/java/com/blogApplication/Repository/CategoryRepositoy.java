package com.blogApplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogApplication.Entity.Category;

public interface CategoryRepositoy extends JpaRepository<Category, Integer>{

}
