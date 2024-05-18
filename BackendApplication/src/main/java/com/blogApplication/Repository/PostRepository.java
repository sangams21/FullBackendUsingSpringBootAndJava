package com.blogApplication.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.blogApplication.Entity.Category;
import com.blogApplication.Entity.Post;
import com.blogApplication.Entity.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{
	
	
	List<Post>  findByUser(User user);
	
	List<Post>  findByCategory(Category category);
	
	@Query("select p from Post p where p.title like :key")
	List<Post> searchByTitle(@Param("key") String title);

}
