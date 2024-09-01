package com.shopease.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopease.dto.CategoryDto;
import com.shopease.dto.UserDto;
import com.shopease.security.service.JwtService;
import com.shopease.service.CategoryService;
import com.shopease.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private JwtService jwtService;
	
	@GetMapping("/sellers/all")
	public ResponseEntity<List<UserDto>> getAllSellers(){
		List<UserDto> sellers = userService.getAllSellers();
		return ResponseEntity.ok(sellers);
	}
	
	@PostMapping("/category/add")
	public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
		CategoryDto savedCategoryDto = categoryService.addCategory(categoryDto);
		return new ResponseEntity<>(savedCategoryDto, HttpStatus.CREATED);
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		List<UserDto> userDtos = userService.getAllUsers();
		return ResponseEntity.ok(userDtos);
	}
	

	
}
