package com.shopease.service;

import java.util.List;

import com.shopease.dto.ProductDto;

public interface ProductService {

	List<ProductDto> getAllProduct();
	
	ProductDto getProductById(Long productId) throws Exception;
	
	ProductDto deleteProduct(Long productId) throws Exception;
	
	ProductDto updateProduct(ProductDto productDto, Long productId) throws Exception;
	
	ProductDto addProduct(ProductDto productDto);
}
