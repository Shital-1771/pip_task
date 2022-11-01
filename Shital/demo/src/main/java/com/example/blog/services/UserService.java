package com.example.blog.services;

import java.util.List;

import com.example.blog.payload.UserDto;

public interface UserService {
	
	UserDto createUser(UserDto user);
	
	UserDto updateUser(UserDto user, Integer userId);
	
	UserDto getUserById(Integer userId);
	
	List<UserDto> getAllUsers(Integer userId);
	
	void deleteUser(Integer userId);

}

