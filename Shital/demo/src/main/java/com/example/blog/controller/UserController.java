package com.example.blog.controller;

import java.util.List;
//import java.util.Map;

import javax.validation.Valid;

//import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.payload.ApiResponse;
import com.example.blog.payload.UserDto;
import com.example.blog.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/create")
	public ResponseEntity<UserDto>createUser(@Valid @RequestBody UserDto userDto)
	{
		UserDto createUserDto=userService.createUser(userDto);
		return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
		
	}
		
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto>updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userId")Integer uid)
	{
		UserDto updatedUser=this.userService.updateUser(userDto,uid);
		return ResponseEntity.ok(updatedUser);
	}
	
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse>deleteUser(@PathVariable("userId")Integer uid)
	{
		this.userService.deleteUser(uid);
		return new ResponseEntity(new ApiResponse("User deleted successfully",true), HttpStatus.OK);
	}
	
	@GetMapping("/allusers")
	public ResponseEntity<List<UserDto>>getAllUsers()
	{
		return ResponseEntity.ok(this.userService.getAllUsers(null));
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto>getSingleUser(@PathVariable Integer userId)
	{
		return ResponseEntity.ok(this.userService.getUserById(userId));
	}
	
	

}
