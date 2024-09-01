package com.shopease.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopease.dto.UserDto;
import com.shopease.dto.UserUpdateDto;
import com.shopease.security.service.JwtService;
import com.shopease.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtService jwtService;

	@GetMapping("/details")
	public ResponseEntity<?> getUserDetails(@RequestHeader("Authorization") String token) throws Exception {
		if(token==null || token.length() ==0) throw new NullPointerException("Token cannot be null");
		String username = jwtService.getUsername(token.substring(7));
		UserDto userDto = userService.getUserByUsername(username);
		return ResponseEntity.ok(userDto);
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<?> updateUser(@Valid @RequestBody UserUpdateDto updateDto, @RequestHeader("Authorization") String auth, BindingResult result) throws Exception{
		if(result.hasErrors())return ResponseEntity.badRequest().body(result.getFieldErrors().get(0).getDefaultMessage());
		Long userId = jwtService.getUserId(auth.substring(7));
		UserDto updatedDto = userService.updateUser(updateDto, userId);
		String token = jwtService.getToken(updatedDto.getUsername());
		return ResponseEntity.ok(token);
	}
	
	@GetMapping("/h")
	public String getMessage() {
		return "Hello from getMessage";
	}

}
