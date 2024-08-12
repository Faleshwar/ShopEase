package com.shopease.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String country;
	
	private String state;
	
	private String city;
	
	private String pinCode;
	
	private String street;
	
	@OneToMany
	@JsonIgnore
	private List<User> users;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	

	public Address(Long id, String country, String state, String city, String pinCode, String street,
			List<User> users) {
		super();
		this.id = id;
		this.country = country;
		this.state = state;
		this.city = city;
		this.pinCode = pinCode;
		this.street = street;
		this.users = users;
	}

	public Address() {
		
	}
	
	
}
