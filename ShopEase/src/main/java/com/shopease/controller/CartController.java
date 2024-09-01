package com.shopease.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopease.dto.CartDto;
import com.shopease.dto.ProductDto;
import com.shopease.security.service.JwtService;
import com.shopease.service.CartService;
import com.shopease.service.UserService;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserService userService;

	@PostMapping("/add/{id}")
	public ResponseEntity<CartDto> addToCart(@RequestHeader("Authorization") String auth, @PathVariable("id") Long productId) throws Exception{
		String username = jwtService.getUsername(auth.substring(7));
		Long userId = userService.getUserByUsername(username).getId();
		CartDto cartDto = cartService.addToCart(productId, userId);
		return new ResponseEntity<>(cartDto, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<CartDto> removeFromCart(@RequestHeader("Authorization") String auth,@PathVariable("id") Long productId) throws Exception{
		String username = jwtService.getUsername(auth.substring(7));
		Long userId = userService.getUserByUsername(username).getId();
		CartDto cartDto = cartService.removeFromCart(productId, userId);
		return ResponseEntity.ok(cartDto);
	}
	
	@GetMapping("/product/all")
	public ResponseEntity<List<ProductDto>> getProductList(@RequestHeader("Authorization") String auth) throws Exception{
		String username = jwtService.getUsername(auth.substring(7));
		Long userId = userService.getUserByUsername(username).getId();
		List<ProductDto> list = cartService.cartProducts(userId);
		return ResponseEntity.ok(list);
	}

}
