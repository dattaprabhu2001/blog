package com.blog.blog.Services.Impl;

import com.blog.blog.Entity.Category;
import com.blog.blog.Exceptions.ResourceNotFoundException;
import com.blog.blog.Payload.CategoryDto;
import com.blog.blog.Payload.UserDto;
import com.blog.blog.Repos.CategoryRepo;
import com.blog.blog.Services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryRepo categoryRepo;
	@Autowired
	ModelMapper modelMapper;
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = this.modelMapper.map(categoryDto, Category.class);
		Category addedCategory = this.categoryRepo.save(category);
		return this.modelMapper.map(addedCategory, CategoryDto.class);
	}
	
	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category category=this.modelMapper.map(categoryDto,Category.class);
		category.setCategoryTitle(category.getCategoryTitle());
		category.setCategoryDescription(category.getCategoryDescription());
		Category updatedCategory=this.categoryRepo.save(category);
		return this.modelMapper.map(updatedCategory,CategoryDto.class);
	}
	
	@Override
	public void deleteCategory(Integer categoryId) {
	Category category=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId",categoryId));
	this.categoryRepo.delete(category);
	}
	
	@Override
	public CategoryDto getCategory(Integer categoryId) {
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("User","Id",categoryId));
		return this.modelMapper.map(category,CategoryDto.class);
	}
	
	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> categories=this.categoryRepo.findAll();
		List<CategoryDto>categoriesDto=categories.stream().map((category) -> this.modelMapper.map(category,CategoryDto.class)).collect(Collectors.toList());
		
		return categoriesDto;
	}
}
