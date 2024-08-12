package com.shopease.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shopease.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
