package com.shopease.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shopease.dto.ProductDto;
import com.shopease.service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;

	@GetMapping("/product")
	public ResponseEntity<List<ProductDto>> getAllProduct(){
		List<ProductDto> productDtos = productService.getAllProduct();
		return new ResponseEntity<>(productDtos, HttpStatus.OK);
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) throws Exception{
		ProductDto productDto = productService.getProductById(id);
		return new ResponseEntity<>(productDto, HttpStatus.OK);
	}
	
	@DeleteMapping("/v1/product/{id}")
	public ResponseEntity<ProductDto> deleteProduct(@PathVariable Long id) throws Exception{
		ProductDto productDto = productService.deleteProduct(id);
		return new ResponseEntity<>(productDto, HttpStatus.ACCEPTED);
	}
	
	
	
	@PutMapping("/v1/product/{id}")
	public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @RequestBody ProductDto productDto) throws Exception{
		ProductDto updateDto = productService.updateProduct(productDto, id);
		
		return ResponseEntity.ok(updateDto);
	}

}
