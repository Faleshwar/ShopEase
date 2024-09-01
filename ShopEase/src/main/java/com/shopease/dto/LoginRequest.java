package com.shopease.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginRequest {

	@NotBlank(message = "Username is required")
	@Size(min = 2, max = 50, message = "Username must be 2 to 50 charaters")
	private String username;
	
	@NotBlank(message = "Password is required")
	@Size(min = 3, max = 50, message = "Password must be 3 to 50 charaters")
	private String password;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LoginRequest() {
	
	}
	public LoginRequest(String username, String password) {
		
		this.username = username;
		this.password = password;
	}
	
	
	
}
