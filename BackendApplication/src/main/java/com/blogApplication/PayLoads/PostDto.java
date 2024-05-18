package com.blogApplication.PayLoads;

import java.util.Date;

import com.blogApplication.Entity.Category;
import com.blogApplication.Entity.User;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {

	
	private int postId;
	private String title;
	
	private String content;

	private String img;
	private Date editDate;
	
	private CategoryDto category;
	
	private UserDto user;
}
