package com.blog.blog.Services.Impl;

import com.blog.blog.Entity.Category;
import com.blog.blog.Entity.Post;
import com.blog.blog.Entity.User;
import com.blog.blog.Exceptions.ResourceNotFoundException;
import com.blog.blog.Payload.PostDto;
import com.blog.blog.Repos.CategoryRepo;
import com.blog.blog.Repos.PostRepo;
import com.blog.blog.Repos.UserRepo;
import com.blog.blog.Services.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
	private final PostRepo postRepo;
	private final ModelMapper modelMapper;
	private final CategoryRepo categoryRepo;
	private final UserRepo userRepo;
	
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","User Id",userId));
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id",categoryId));


		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setCategory(category);
		post.setUser(user);
		Post savedPost=this.postRepo.save(post);
		return this.modelMapper.map(savedPost,PostDto.class);
	}
	
	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Post Id",postId));
		post.setTitle(post.getTitle());
		post.setContent(post.getContent());
		Post post1=this.postRepo.save(post);
		return this.modelMapper.map(post1,PostDto.class);
	}
	
	@Override
	public void deletePost(Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Post Id",postId));
		this.postRepo.delete(post);
	}
	
	@Override
	public List<PostDto> getAllPost(Integer pageNumber,Integer pageSize) {
		
//		int pageSize=5;
//		int pageNumber=1;
		Pageable p= PageRequest.of(pageSize,pageNumber);
		Page<Post> pagePost =this.postRepo.findAll(p);
		List<Post>allPosts=pagePost.getContent();
		List<PostDto>allPostDtos=allPosts.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return allPostDtos;
	}
	
	@Override
	public PostDto getPostById(Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Post Id",postId));
		PostDto postDto=this.modelMapper.map(post, PostDto.class);
		return postDto;
	}
	
	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category"," Category Id",categoryId));
		List<Post> posts=this.postRepo.findAllByCategory(cat);
		List<PostDto>postDtos=posts.stream().map((post) ->this.modelMapper.map(post, PostDto.class) ).collect(Collectors.toList());
		return postDtos;
	}
	
	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User" ,"User Id",userId));
		List<Post> posts=this.postRepo.findAllByUser(user);
		List<PostDto> postDtos=posts.stream().map((post )->this.modelMapper.map(post,PostDto.class) ).collect(Collectors.toList());
		
	return postDtos;
	}
	
	@Override
	public List<PostDto> searchPosts(String keyword) {
		return null;
	}
}
