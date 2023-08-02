package com.blog.blog.Payload;

import com.blog.blog.Entity.Category;
import com.blog.blog.Entity.User;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PostDto {
	private String title;
	private String content;
	private String imageName="default.png";
	private Date addedDate;
	private CategoryDto categoryDto;
	private UserDto userDto;
}
