package com.shopease.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shopease.authentication.AuthenticationResponse;
import com.shopease.dto.LoginRequest;
import com.shopease.dto.UserDto;
import com.shopease.serviceImpl.AuthenticationService;

import jakarta.validation.Valid;

@RestController
public class AuthenticationController {
	
	
	
	@Autowired
	private AuthenticationService authenticationService;

	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody UserDto userDto, BindingResult result) {
		if(result.hasErrors()) {
			System.out.println("Error");
			return ResponseEntity.badRequest().body(result.getFieldErrors());
		}
		AuthenticationResponse response = authenticationService.register(userDto);
		return ResponseEntity.ok(response);
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest, BindingResult result){
		if(result.hasErrors())return ResponseEntity.badRequest().body(result.getFieldErrors());
		AuthenticationResponse response = authenticationService.login(loginRequest);
		return ResponseEntity.ok(response);
	}
	

}
