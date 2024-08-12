
package com.shopease.dto;

import com.shopease.model.Category;

public class ProductDto {

	private Long id;
	
	private String name;
	
	private String image;
	
	private String description;
	
	private Double price;
	
	private Integer stock;
	
	private Category category;
	

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	

	public ProductDto(Long id, String name, String image, String description, Double price, Integer stock,
			Category category) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.description = description;
		this.price = price;
		this.stock = stock;
		this.category = category;
	}

	public ProductDto() {
	
	}
	
	

}
