package com.shopease.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopease.dto.ProductDto;
import com.shopease.service.ProductService;

@RestController
@RequestMapping("/seller")
public class SellerController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/product/add")
	public ResponseEntity<ProductDto> addProduct(@RequestBody ProductDto productDto) {
		ProductDto addeProductDto = productService.addProduct(productDto);
		return new ResponseEntity<>(addeProductDto, HttpStatus.ACCEPTED);
	}

}
