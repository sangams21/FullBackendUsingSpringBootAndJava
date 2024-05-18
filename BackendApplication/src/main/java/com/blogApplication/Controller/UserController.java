package com.blogApplication.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogApplication.PayLoads.ApiResponse;
import com.blogApplication.PayLoads.UserDto;
import com.blogApplication.Services.UserServices;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserServices userServices;
	
	// post create User 
	@PostMapping("/addUser")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody  UserDto userDto) {
		
		UserDto createUserDto=this.userServices.createUser(userDto);
		return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);		
	}
	
	//put - update user 
	
	@PutMapping("/updateUser/{id}")
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable int id){
		UserDto updatedUSer=this.userServices.updateUser(userDto, id);
		return new ResponseEntity<>(updatedUSer,HttpStatus.OK);
	
	}
	
	// delete User 
	
	@DeleteMapping("/deleteUser/{id}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable int id){
		this.userServices.deleteUser(id);
		return new ResponseEntity<ApiResponse>(new ApiResponse("user deleted successfully",true),HttpStatus.OK);
	}
	
	//get user
	@GetMapping("/getUser")
	public ResponseEntity<List<UserDto>> getAllUser() {
		return ResponseEntity.ok(this.userServices.getAllUser());
	}
	
	@GetMapping("/getUser/{id}")
	public ResponseEntity<UserDto> getUser(@PathVariable int id) {
		return ResponseEntity.ok(this.userServices.getUserById(id));
	}


}
