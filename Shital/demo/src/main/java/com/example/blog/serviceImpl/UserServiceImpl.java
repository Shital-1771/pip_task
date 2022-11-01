package com.example.blog.serviceImpl;
import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.Collectors;

import javax.management.loading.PrivateClassLoader;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.example.blog.entity.User;
import com.example.blog.payload.UserDto;
import com.example.blog.repository.UserRepo;
import com.example.blog.services.UserService;


import com.example.blog.exception.*;


@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = dtoToUser(userDto);
		User savedUser= userRepo.save(user);
		
		return userToDto(savedUser);
	}

		/*
		 * UserDto dto=new UserDto(); BeanUtils.copyProperties(savedUser, dto);
		 */

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","id",userId));
		
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		User updatedUser=this.userRepo.save(user);
		this.userToDto(updatedUser);
		UserDto userDto1=this.userToDto(updatedUser);
		
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","id",userId));
		return this.userToDto(user);
	}
	
	

	@Override
	public List<UserDto> getAllUsers(Integer userId) {
		
		private int pageNumber;
		private int pageSize;
		Pageable p= PageRequest.of(pageSize, pageNumber);
		page<User> pageUser=this.userRepo.findAll(p);
		List<User> users=this.userRepo.findAll();
		List<UserDto>userDtos=users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}
	
	
	
	
	
	
	
	

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user","id",userId));
		this.userRepo.delete(user);

	}
	
	private User dtoToUser(UserDto userDto) {
		User user= this.modelMapper.map(userDto,User.class);
		
		/*user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());              **converting one class object to another object class using model-mapper class
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());*/
		return user;
		
	}
	
	private UserDto userToDto(User user){
		UserDto userDto =this.modelMapper.map(user,UserDto.class);
		
		/*userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		userDto.setAbout(user.getAbout());*/
		return userDto;
	}
	

}
