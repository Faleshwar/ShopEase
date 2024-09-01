package com.shopease.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shopease.dto.AddressDto;
import com.shopease.dto.UserDto;
import com.shopease.dto.UserUpdateDto;
import com.shopease.model.Address;
import com.shopease.model.Role;
import com.shopease.model.User;
import com.shopease.repository.AddressRepository;
import com.shopease.repository.UserRepository;
import com.shopease.service.UserService;

import ch.qos.logback.core.joran.conditional.IfAction;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private PasswordEncoder encoder;

	public UserDto convertToDto(User user) {
		return new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getUsername(), user.getPassword(), user.getEmail(), user.getPhoneNo(), user.getRole(), user.getAddress(), user.getProducts(), user.getCart());
	}
	
	public User convertToEntity(UserDto userDto) {
		return new User(userDto.getId(), userDto.getFirstName(), userDto.getLastName(), userDto.getUsername(), userDto.getPassword(), userDto.getEmail(), userDto.getPhoneNo(), userDto.getRole(), userDto.getAddress(), userDto.getProducts(), userDto.getCart());
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = userRepository.findAll();
		List<User> onlyUsers = users.stream().filter((u)->u.getRole().equals(Role.USER)).collect(Collectors.toList());
		return onlyUsers.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	@Override
	public UserDto addUser(UserDto userDto) {
		String password = encoder.encode(userDto.getPassword());
		Address inputAddress = userDto.getAddress();
		Optional<Address> addressOptional = addressRepository.findAddress(inputAddress.getCountry(), inputAddress.getState(), inputAddress.getCity(), inputAddress.getStreet(), inputAddress.getPinCode());
		if(addressOptional.isPresent()) {
			Address existAddress = addressOptional.get();
			userDto.setAddress(existAddress);
		}else {
			userDto.setAddress(inputAddress);
		}
		userDto.setRole(Role.USER);
		userDto.setPassword(password);
		User user = convertToEntity(userDto);
		User savedUser = userRepository.save(user);
		return convertToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserUpdateDto userUpdateDto, Long userId) throws Exception {
	
		User user = userRepository.findById(userId).orElseThrow(()->new RuntimeException("User not found"));
		
		if(userUpdateDto.getFirstName() != null) {
			user.setFirstName(userUpdateDto.getFirstName());
		}
		if(userUpdateDto.getLastName() != null) {
			user.setLastName(userUpdateDto.getLastName());
		}
		
		if(userUpdateDto.getEmail() != null) {
			user.setEmail(userUpdateDto.getEmail());
		}
		
		if(userUpdateDto.getPhoneNo() != null) {
			user.setPhoneNo(userUpdateDto.getPhoneNo());
		}
		
		if(userUpdateDto.getUsername() != null) {
			user.setUsername(userUpdateDto.getUsername());
		}
		
		if(userUpdateDto.getAddressDto() != null) {
			Address currentAddress = user.getAddress();
			Address updatedAddress = updateAddress(userUpdateDto.getAddressDto(), currentAddress);
			user.setAddress(updatedAddress);
		}
		
		User savedUser = userRepository.save(user);
		
		
		return convertToDto(savedUser);
	}
	
	private Address updateAddress(AddressDto addressDto, Address currentAddress) {
		
		if(addressDto.getState() != null) {
			currentAddress.setState(addressDto.getState());
		}
		if(addressDto.getCity() != null) {
			currentAddress.setCity(addressDto.getCity());
		}
		if(addressDto.getCountry() != null) {
			currentAddress.setCountry(addressDto.getCountry());
		}
		if(addressDto.getStreet() != null) {
			currentAddress.setStreet(addressDto.getStreet());
		}
		
		if(addressDto.getPinCode() != null) {
			currentAddress.setPinCode(addressDto.getPinCode());
		}
		
		return currentAddress;
	}

//	Delete user by id
	
	@Override
	public UserDto deleteUser(Long userId) throws Exception {
		UserDto userDto = getUserById(userId);
		userRepository.delete(convertToEntity(userDto));
		return userDto;
	}
	
//	Get User by id

	@Override
	public UserDto getUserById(Long userId) throws Exception {
		Optional<User> optionalUser = userRepository.findById(userId);
		if(optionalUser.isPresent()) {
			return convertToDto(optionalUser.get());
		}
		throw new Exception("User not found");
	}

	@Override
	public UserDto getUserByUsername(String username) throws Exception {
		Optional<User> optionalUser= userRepository.findByUsername(username);
		if(!optionalUser.isPresent()) {
			throw new Exception("User not found");
		}
		return convertToDto(optionalUser.get());
	}

	@Override
	public List<UserDto> getAllSellers() {
		List<User> users = userRepository.findAll();
		List<User> sellers = users.stream().filter((u)->u.getRole().equals(Role.SELLER)).collect(Collectors.toList());
		return sellers.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	
}
