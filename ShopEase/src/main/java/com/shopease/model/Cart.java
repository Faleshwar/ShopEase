package com.shopease.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDateTime createdAt;
	 
	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "carts")
	private Set<Product> products = new HashSet<>();
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@JsonIgnore
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

	public Cart(Long id, LocalDateTime createdAt, Set<Product> products, User user) {
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.products = products;
		this.user = user;
	}

	public Cart() {
		
	}

	@Override
	public String toString() {
		return "Cart [id=" + id + ", createdAt=" + createdAt + ", products=" + products + ", user=" + user + "]";
	}
	
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return Objects.hash(id);
	}
	

}
