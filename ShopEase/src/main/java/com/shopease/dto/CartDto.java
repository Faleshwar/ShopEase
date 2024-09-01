package com.shopease.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.shopease.model.Product;
import com.shopease.model.User;

public class CartDto {

	private Long id;

	private LocalDateTime createdAt;

	private Set<Product> products = new HashSet<>();

	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public CartDto(Long id, LocalDateTime createdAt, Set<Product> products, User user) {
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.products = products;
		this.user = user;
	}

	public CartDto() {
	
	}
	
	
}
