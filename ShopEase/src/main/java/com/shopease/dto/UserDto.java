package com.shopease.dto;

import java.util.List;


import com.shopease.model.Address;
import com.shopease.model.Cart;
import com.shopease.model.Product;
import com.shopease.model.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDto {
	
	private Long id;

	@NotBlank(message = "First name is required")
	@Size(min = 3, max = 50, message = "First name must be between 3 to 50 characters")
	private String firstName;

	@NotBlank(message = "Last name is required")
	@Size(min = 3, max = 50, message = "Last name must be between 3 to 50 characters")
	private String lastName;

	@NotBlank(message = "Username is required")
	@Size(min = 3, max = 50, message = "Username must be between 3 to 50 characters")
	private String username;

	@NotBlank(message = "Password is required")
	@Size(min = 3, max = 50, message = "Password must be between 3 to 50 characters")
	private String password;

	@NotBlank(message = "Email is required")
	@Email(message = "Email should be valid")
	private String email;

	@NotBlank(message = "Phone number is required")
	private String phoneNo;

	private Role role;

	@NotBlank(message = "Address is required")
	private Address address;
	
	private List<Product> products;
	
	private Cart cart;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	

	

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public UserDto() {
		
	}

	public UserDto(Long id, String firstName, String lastName, String username, String password, String email,
			String phoneNo, Role role, Address address, List<Product> products, Cart cart) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.phoneNo = phoneNo;
		this.role = role;
		this.address = address;
		this.products = products;
		this.cart = cart;
	}


}
