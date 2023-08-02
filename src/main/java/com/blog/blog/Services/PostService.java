package com.blog.blog.Services;

import com.blog.blog.Entity.Post;
import com.blog.blog.Payload.PostDto;

import java.util.List;

public interface PostService {
	PostDto createPost(PostDto postDto,Integer userId, Integer categoryId);
	PostDto updatePost(PostDto postDto,Integer postId);
	void deletePost(Integer postId);
	List<PostDto> getAllPost(Integer pageNumber,Integer pageSize);
	PostDto getPostById(Integer postId);
	List<PostDto>getPostsByCategory(Integer categoryId);
	List<PostDto>getPostsByUser(Integer userId);
	List<PostDto>searchPosts(String keyword);
	
}
