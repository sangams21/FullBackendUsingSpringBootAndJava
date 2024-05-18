package com.blogApplication.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Category")
@NoArgsConstructor
@Getter
@Setter
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cId;
	@Column(name="ctitle",length = 100)
	private String cTitle;
	@Column(name="cdiscription")
	private String cDiscription;
	
	
	@OneToMany(mappedBy = "category" ,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	//casecade type means if we delet it will deleted or update all child object as well with perent object
	private List<Post> posts=new ArrayList<>();
	
}
