package com.blogApplication.Entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.blogApplication.PayLoads.CommentDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int postId;
	
	@Column(name="post_title",length = 100,nullable = false)
	private String title;
	
	private String content;
	
	private String img;
	
	private Date editDate;
	
	
	@ManyToOne
	@JoinColumn(name="category_id" )
	private Category category;
	
	@ManyToOne
	private User user;

     @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
	private Set<Comment> comments=new HashSet<>();
}
