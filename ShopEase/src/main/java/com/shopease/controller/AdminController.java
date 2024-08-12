package com.shopease.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopease.dto.UserDto;
import com.shopease.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/sellers/all")
	public ResponseEntity<List<UserDto>> getAllSellers(){
		List<UserDto> sellers = userService.getAllSellers();
		return ResponseEntity.ok(sellers);
	}

}
