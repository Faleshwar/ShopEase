package com.shopease.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserUpdateDto {

	@NotBlank(message = "First name is required")
	@Size(min = 3, max = 50, message = "First name must be 3 to 50 characters")
	private String firstName;
	
	@NotBlank(message = "Last name is required")
	@Size(min = 3, max = 50, message = "Last name must be 3 to 50 characters")
	private String lastName;
	
	@NotBlank(message = "Username is required")
	@Size(min = 3, max = 50, message = "Username must be 3 to 50 characters")
	private String username;
	
	@NotBlank(message = "Email is required")
	@Email(message = "Email must be valid")
	private String email;
	
	@NotBlank(message = "Phone number is required")
	@Size(min = 3, max = 50, message = "Phone number must be 3 to 15 characters")
	private String phoneNo;
	
	private AddressDto addressDto;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public AddressDto getAddressDto() {
		return addressDto;
	}

	public void setAddressDto(AddressDto addressDto) {
		this.addressDto = addressDto;
	}

	public UserUpdateDto(String firstName, String lastName, String username, String email, String phoneNo,
			AddressDto addressDto) {
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.phoneNo = phoneNo;
		this.addressDto = addressDto;
	}

	public UserUpdateDto() {
		
	}
	
	
	
}
