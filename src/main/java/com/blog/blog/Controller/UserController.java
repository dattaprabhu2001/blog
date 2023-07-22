package com.blog.blog.Controller;

import com.blog.blog.Payload.ApiResponse;
import com.blog.blog.Payload.UserDto;
import com.blog.blog.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
private final UserService userService;
	//POST-create User
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
		UserDto createdUser=this.userService.createUser(userDto);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}
	//PUT-update User
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto>updateUser(@RequestBody UserDto userDto,@PathVariable int userId){
		UserDto updateUser=this.userService.updateUser(userDto,userId);
		return  ResponseEntity.ok(updateUser);
	}
	//DELETE-delete User
	@DeleteMapping("/{userId}")
	public  ResponseEntity<?>deleteUser(@PathVariable int userId){
		this.userService.deleteUser(userId);
		return new ResponseEntity<>(new ApiResponse("User Deleted Successfully",true),HttpStatus.OK);
	}
	//GET-get User
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto>getUser(@PathVariable int uid){
		return  ResponseEntity.ok(this.userService.getUserById(uid));
	}
	//GET-get UserAlL
	@GetMapping("/")
	public ResponseEntity<List<UserDto>>getAllUser(){
		return ResponseEntity.ok(this.userService.getAllUsers());
	}
}
