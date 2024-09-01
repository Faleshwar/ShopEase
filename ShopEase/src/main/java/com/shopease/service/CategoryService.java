package com.shopease.service;

import java.util.List;

import com.shopease.dto.CategoryDto;


public interface CategoryService {

	CategoryDto addCategory(CategoryDto categoryDto);
	
	List<CategoryDto> getAllCategories();
}
