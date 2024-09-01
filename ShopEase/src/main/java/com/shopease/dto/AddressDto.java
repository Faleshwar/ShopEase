package com.shopease.dto;

public class AddressDto {

	private String country;
	
	private String state;
	
	private String city;
	
	private String pinCode;
	
	private String street;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public AddressDto(String country, String state, String city, String pinCode, String street) {
		this.country = country;
		this.state = state;
		this.city = city;
		this.pinCode = pinCode;
		this.street = street;
	}

	public AddressDto() {
		
	}
	
	
}
