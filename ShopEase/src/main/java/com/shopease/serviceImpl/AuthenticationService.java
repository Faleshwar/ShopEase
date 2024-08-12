package com.shopease.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.shopease.authentication.AuthenticationResponse;
import com.shopease.dto.LoginRequest;
import com.shopease.dto.UserDto;
import com.shopease.model.User;
import com.shopease.repository.UserRepository;
import com.shopease.security.service.JwtService;
import com.shopease.service.UserService;

@Service
public class AuthenticationService {

	
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public AuthenticationResponse register(UserDto userDto) {
		UserDto savedUserDto= userService.addUser(userDto);
		String token = jwtService.getToken(savedUserDto.getUsername());
		
		return new AuthenticationResponse(token, "Success");
	}
	
	public AuthenticationResponse login(LoginRequest loginRequest) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		User user = userRepository.findByUsername(loginRequest.getUsername()).orElseThrow();
		String token = jwtService.getToken(user.getUsername());
		return new AuthenticationResponse(token, "Success");
	}
}
