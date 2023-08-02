package com.blog.blog.Services;

import com.blog.blog.Payload.CategoryDto;
import com.blog.blog.Repos.CategoryRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CategoryService {
	//create
	 CategoryDto createCategory(CategoryDto categoryDto);
	//update
	 CategoryDto updateCategory( CategoryDto categoryDto, Integer categoryId );
	//delete
	 void deleteCategory(Integer categoryId );
	//get
	 CategoryDto getCategory(Integer categoryId);
	//getAll
	 List<CategoryDto> getAllCategory();
}
