package com.shopease.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shopease.authentication.AuthenticationResponse;
import com.shopease.dto.LoginRequest;
import com.shopease.dto.UserDto;
import com.shopease.serviceImpl.AuthenticationService;

@RestController
public class AuthenticationController {
	
	
	
	@Autowired
	private AuthenticationService authenticationService;

	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@RequestBody UserDto userDto) {
		AuthenticationResponse response = authenticationService.register(userDto);
		return ResponseEntity.ok(response);
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest loginRequest){
		AuthenticationResponse response = authenticationService.login(loginRequest);
		return ResponseEntity.ok(response);
	}
	

}
