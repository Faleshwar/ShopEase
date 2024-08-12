package com.shopease.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String image;
	
	@Column(length = 1000)
	private String description;
	
	private Double price;
	
	private Integer stock;
	
	@ManyToOne(cascade = jakarta.persistence.CascadeType.ALL)
	@JoinColumn(name = "category_id", referencedColumnName = "id")
	private Category category;

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
	
	

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	

	public Product(Long id, String name, String image, String description, Double price, Integer stock,
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

	public Product() {
	
	}
	
	

}
