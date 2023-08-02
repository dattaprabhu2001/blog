package com.blog.blog.Controller;

import com.blog.blog.Entity.Post;
import com.blog.blog.Payload.ApiResponse;
import com.blog.blog.Payload.PostDto;
import com.blog.blog.Services.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {
	private final ModelMapper modelMapper;
	private final PostService postService;
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId) {
		
		PostDto post = this.postService.createPost(postDto,userId,categoryId);
		
		return new ResponseEntity<PostDto>(post, HttpStatus.CREATED);
	}
	@GetMapping("/userId/{userId}/posts")
	public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId){
		List<PostDto> posts= this.postService.getPostsByUser(userId);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	@GetMapping("/categoryId/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId){
		List<PostDto> posts=this.postService.getPostsByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	@GetMapping("/all")
	public ResponseEntity<List<PostDto>> getAllPost(@RequestParam (value = "pageNumber",defaultValue = "5",required = false)Integer pageNumber,
													@RequestParam(value = "pageSize",defaultValue = "1",required = false)Integer pageSize){
		List<PostDto> posts=this.postService.getAllPost(pageNumber,pageSize);
		return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
	}
	@GetMapping("/postId/{postId}")
	public ResponseEntity<PostDto> getByPostId(@PathVariable Integer postId){
		PostDto postDto=this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
	}
	@DeleteMapping("/posts/{postId}")
	public ApiResponse deletePost(@PathVariable Integer postId){
		this.postService.deletePost(postId);
		return new ApiResponse("Post deleted successfully",true);
	}
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(@PathVariable Integer postId,@RequestBody PostDto postDto){
		PostDto updatedPostDto=this.postService.updatePost(postDto,postId);
		return new ResponseEntity<PostDto>(updatedPostDto,HttpStatus.OK);
	}
}
