package com.blog.blog.Services;

import com.blog.blog.Payload.UserDto;

import java.util.List;

public interface UserService {
	
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user,Integer userId);
	UserDto getUserById(Integer UserId);
	List<UserDto> getAllUsers();
	void deleteUser(Integer UserId);
	
}
