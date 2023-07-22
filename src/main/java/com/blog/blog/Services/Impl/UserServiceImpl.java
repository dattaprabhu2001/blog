package com.blog.blog.Services.Impl;

import com.blog.blog.Entity.User;
import com.blog.blog.Exceptions.ResourceNotFoundException;
import com.blog.blog.Payload.UserDto;
import com.blog.blog.Repos.UserRepo;
import com.blog.blog.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	private final UserRepo userRepo;
	@Override
	public UserDto createUser(UserDto userDto) {
		User user=this.dtoToUser(userDto);
		User savedUser=this.userRepo.save(user);
		
		return this.userToDto(savedUser);
	}
	
	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
		user.setName(user.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(user.getPassword());
		user.setAbout(userDto.getAbout());
		User updateUser=this.userRepo.save(user);
		
		return this.userToDto(updateUser);
	}
	
	@Override
	public UserDto getUserById(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		
		return this.userToDto(user);
	}
	
	@Override
	public List<UserDto> getAllUsers() {
		List<User> listOfUsers=this.userRepo.findAll();
		List<UserDto>listOfUserDto=listOfUsers.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		return listOfUserDto;
	}
	
	@Override
	public void deleteUser(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","Id",userId));
		this.userRepo.delete(user);
	}
	public User dtoToUser(UserDto userDto){
		User user=new User();
		user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		return user;
	}
	public UserDto userToDto(User user){
		UserDto userDto=new UserDto();
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		userDto.setAbout(user.getAbout());
		return userDto;
	}
}
