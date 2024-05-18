package com.blogApplication.Services.Impl;


import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogApplication.Entity.User;
import com.blogApplication.PayLoads.UserDto;
import com.blogApplication.Repository.UserRepository;
import com.blogApplication.Services.UserServices;
import com.blogApplication.Exception.ResourceNotFoundExeption;

@Service
public class UserServiceImp implements UserServices{

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public UserDto createUser(UserDto userDto) {       
		User user=this.dtoToUser(userDto);	
		User savedUser=this.userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer id) {
		
		User user =this.userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundExeption("User","id",id));
		
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		user.setEmail(userDto.getEmail());
		User updateUser=this.userRepo.save(user);
		
		
		return this.userToDto(updateUser);
	}

	@Override
	public UserDto getUserById(Integer id) {

		User user=this.userRepo.findById(id).orElseThrow(()->new ResourceNotFoundExeption("user", "id", id));		
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUser() {

		List<User> users=this.userRepo.findAll();
	List<UserDto>	userDto=users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return userDto;
	}

	@Override
	public void deleteUser(Integer id) {

		User user=this.userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundExeption("user", "id", id));
		
		this.userRepo.delete(user);
		
	}
	
	// used to convert dtoPlaylod to user entity
	private User dtoToUser(UserDto userDto) {
		
		
		User user=this.modelMapper.map(userDto, User.class);
		
	// insted of using normal approch we can use modelMapper 
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		return user;
	}
	//used to convert user entity to dtoPlaylod 
	private UserDto userToDto(User user) {
		
		UserDto userDto=this.modelMapper.map(user, UserDto.class);
		
		// insted of using normal approch we can use modelMapper 
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setAbout(user.getAbout());
//		userDto.setPassword(user.getPassword());
		return userDto;
		
		
		
	}

}
