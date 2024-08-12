package com.shopease.model;


import com.shopease.constant.OrderStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_p")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Double tottleAmount;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getTottleAmount() {
		return tottleAmount;
	}

	public void setTottleAmount(Double tottleAmount) {
		this.tottleAmount = tottleAmount;
	}

//	public OrderStatus getStatus() {
//		return status;
//	}
//
//	public void setStatus(OrderStatus status) {
//		this.status = status;
//	}

	public Order(Long id, Double tottleAmount) {
		super();
		this.id = id;
		this.tottleAmount = tottleAmount;
//		this.status = status;
	}

	public Order() {
	
	}
	

}
