package com.shopease.service;

import java.util.List;

import com.shopease.dto.CartDto;
import com.shopease.dto.ProductDto;

public interface CartService {
	
	List<ProductDto> cartProducts(Long userId) throws Exception;

	CartDto createCart(CartDto cartDto);
	
	CartDto addToCart(Long productId, Long userId) throws Exception;
	
	void deleteCart(Long cartId) throws Exception;
	
	CartDto removeFromCart(Long productId, Long userId)throws Exception; 
}
