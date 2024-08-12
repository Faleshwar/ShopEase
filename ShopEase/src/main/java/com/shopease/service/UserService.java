package com.shopease.service;

import java.util.List;

import com.shopease.dto.UserDto;

public interface UserService {
	
	List<UserDto> getAllUsers();
	
	UserDto addUser(UserDto userDto);
	
	UserDto updateUser(UserDto userDto, Long userId) throws Exception;
	
	UserDto deleteUser(Long userId) throws Exception;
	
	UserDto getUserById(Long userId) throws Exception;
	
	UserDto getUserByUsername(String username)throws Exception;
	
	List<UserDto> getAllSellers();


}
