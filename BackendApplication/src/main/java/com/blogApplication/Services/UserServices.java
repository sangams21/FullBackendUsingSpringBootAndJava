package com.blogApplication.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blogApplication.PayLoads.UserDto;

@Service
public interface UserServices {
	

	UserDto createUser(UserDto user);
	
	UserDto updateUser(UserDto user, Integer id);
	
	UserDto getUserById(Integer id);
	List<UserDto> getAllUser();
	
	void deleteUser(Integer id);
	
	
}
