package com.blog.blog.Controller;

import com.blog.blog.Entity.Category;
import com.blog.blog.Payload.ApiResponse;
import com.blog.blog.Payload.CategoryDto;
import com.blog.blog.Services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {
	private final CategoryService categoryService;
	//create
	@PostMapping("/")
	public ResponseEntity<CategoryDto>createCategory(@RequestBody CategoryDto categoryDto){
		CategoryDto createdCategory=this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
	}
	
	//update
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto>updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer categoryId){
		CategoryDto updatedCategory=this.categoryService.updateCategory(categoryDto,categoryId);
		return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
	}
	
	//delete
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId){
	this.categoryService.deleteCategory(categoryId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category Deleted Successfully",true),HttpStatus.OK);
	}
	
	//get
	@GetMapping("/categoryId/{categoryId}")
	public ResponseEntity<CategoryDto>getCategory(@PathVariable Integer categoryId){
		return ResponseEntity.ok(this.categoryService.getCategory(categoryId));
	}
	
	//getAll
	@GetMapping("/all")
	public ResponseEntity<List<CategoryDto>>categories(){
		return ResponseEntity.ok(this.categoryService.getAllCategory());
		
	}
}
