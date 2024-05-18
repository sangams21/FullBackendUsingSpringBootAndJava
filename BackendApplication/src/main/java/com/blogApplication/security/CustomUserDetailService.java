package com.blogApplication.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.blogApplication.Entity.User;
import com.blogApplication.Exception.ResourceNotFoundExeption;
import com.blogApplication.Repository.UserRepository;

public class CustomUserDetailService implements UserDetailsService{

	
	@Autowired
	private UserRepository userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		// loading user from database by userName
		
		User user = this.userRepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundExeption("email", "email :"+username, 0));
		return user;
	}

}
