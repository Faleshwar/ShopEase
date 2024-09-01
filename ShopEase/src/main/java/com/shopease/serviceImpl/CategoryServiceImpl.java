package com.shopease.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopease.dto.CategoryDto;
import com.shopease.model.Category;
import com.shopease.repository.CategoryRepository;
import com.shopease.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	private Category convertToEntity(CategoryDto categoryDto) {
		return new Category(categoryDto.getId(), categoryDto.getName());
	}
	
	private CategoryDto convertToDto(Category category) {
		return new CategoryDto(category.getId(), category.getName());
	}

	@Override
	public CategoryDto addCategory(CategoryDto categoryDto) {
		
		Category savedCategory = categoryRepository.save(convertToEntity(categoryDto));
		return convertToDto(savedCategory);
	}

	@Override
	public List<CategoryDto> getAllCategories() {
		List<Category> categories = categoryRepository.findAll();
		return categories.stream().map(this::convertToDto).collect(Collectors.toList());
	}

}
