package com.blogApplication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogApplication.Entity.Comment;

public interface CoomenRespo extends JpaRepository<Comment, Integer> {

}
