package com.shopease.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.shopease.dto.UserDto;
import com.shopease.security.service.JwtService;
import com.shopease.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtService jwtService;

	@GetMapping("/user")
	public ResponseEntity<UserDto> getUserDetails(@RequestHeader("Authorization") String token) throws Exception {
		if(token==null || token.length() ==0) throw new NullPointerException("Token cannot be null");
		String username = jwtService.getUsername(token.substring(7));
		UserDto userDto = userService.getUserByUsername(username);
		return new ResponseEntity<>(userDto, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<UserDto>> getAllUsers(){
		List<UserDto> userDtos = userService.getAllUsers();
		return ResponseEntity.ok(userDtos);
	}

}
