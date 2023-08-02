package com.blog.blog.Repos;

import com.blog.blog.Entity.Category;
import com.blog.blog.Entity.Post;
import com.blog.blog.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {
	
	List<Post> findAllByUser(User user);
	List<Post> findAllByCategory(Category category);
}
