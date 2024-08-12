package com.shopease.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shopease.dto.UserDto;
import com.shopease.model.Address;
import com.shopease.model.Role;
import com.shopease.model.User;
import com.shopease.repository.AddressRepository;
import com.shopease.repository.UserRepository;
import com.shopease.service.AddressService;
import com.shopease.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AddressRepository addressRepository;
	@Autowired
	private PasswordEncoder encoder;

	private UserDto convertToDto(User user) {
		return new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getUsername(), user.getPassword(), user.getEmail(), user.getPhoneNo(), user.getRole(), user.getAddress());
	}
	
	private User convertToEntity(UserDto userDto) {
		return new User(userDto.getId(), userDto.getFirstName(), userDto.getLastName(), userDto.getUsername(), userDto.getPassword(), userDto.getEmail(), userDto.getPhoneNo(), userDto.getRole(), userDto.getAddress());
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
	public UserDto updateUser(UserDto userDto, Long userId) throws Exception {
	
		UserDto requestUserDto = getUserById(userId);
		
		if(userDto.getFirstName() != null) {
			requestUserDto.setFirstName(userDto.getFirstName());
		}
		if(userDto.getLastName() != null) {
			requestUserDto.setLastName(userDto.getLastName());
		}
		
		if(userDto.getEmail() != null) {
			requestUserDto.setEmail(userDto.getEmail());
		}
		
		if(userDto.getPhoneNo() != null) {
			requestUserDto.setPhoneNo(userDto.getPhoneNo());
		}
		
		if(userDto.getAddress() != null) {
			requestUserDto.setAddress(updateAddress(userDto, requestUserDto));
		}
		
		userRepository.save(convertToEntity(requestUserDto));
		
		return requestUserDto;
	}
	
	private Address updateAddress(UserDto userDto, UserDto requestDto) {
		Address currAddress = requestDto.getAddress();
		Address newAddress = userDto.getAddress();
		if(newAddress.getCity() != null) {
			currAddress.setCity(newAddress.getCity());
		}
		if(newAddress.getCountry() != null) {
			currAddress.setCountry(newAddress.getCountry());
		}
		if(newAddress.getStreet() != null) {
			currAddress.setStreet(newAddress.getStreet());
		}
		
		if(newAddress.getPinCode() != null) {
			currAddress.setPinCode(newAddress.getPinCode());
		}
		
		return newAddress;
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
