package com.shopease.serviceImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shopease.dto.CartDto;
import com.shopease.dto.ProductDto;
import com.shopease.model.Cart;
import com.shopease.model.Product;
import com.shopease.model.User;
import com.shopease.repository.CartRepository;
import com.shopease.repository.ProductRepository;
import com.shopease.repository.UserRepository;
import com.shopease.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private ProductServiceImpl productService;

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;

	private Cart convertToEntity(CartDto cartDto) {
		return new Cart(cartDto.getId(), cartDto.getCreatedAt(), cartDto.getProducts(), cartDto.getUser());
	}

	private CartDto convertToDto(Cart cart) {
		return new CartDto(cart.getId(), cart.getCreatedAt(), cart.getProducts(), cart.getUser());
	}

	@Override
	public CartDto createCart(CartDto cartDto) {
		Cart cart = new Cart();
		cartRepository.save(cart);
		return convertToDto(cart);
	}

	@Override
	public CartDto addToCart(Long productId, Long userId) throws Exception {

		Optional<User> optionalUser = userRepository.findById(userId);
		if (optionalUser.isEmpty()) {
			throw new UsernameNotFoundException("User not found");
		}
		User user = optionalUser.get();
		Product product = productRepository.findById(productId).get();
		Cart cart = user.getCart();
		if (cart == null) {
			cart = new Cart();
			cart.setCreatedAt(LocalDateTime.now());
			cart.setUser(user);
			user.setCart(cart);
		} else {
			cart = user.getCart();
		}
		Set<Product> listProducts = cart.getProducts();
		listProducts.add(product);
		List<Cart> cartsList = product.getCarts();
		cartsList.add(cart);
		Cart savedCart = cartRepository.save(cart);
		return convertToDto(savedCart);
	}

	@Override
	public void deleteCart(Long cartId) throws Exception {
		Optional<Cart> optionalCart = cartRepository.findById(cartId);
		if (optionalCart.isPresent()) {
			cartRepository.delete(optionalCart.get());
		}

		throw new Exception("Cart not found");
	}

	@Override
	public CartDto removeFromCart(Long productId, Long userId) throws Exception {
		Optional<Product> optionalProduct = productRepository.findById(productId);
		Optional<User> optionalUser = userRepository.findById(userId);

		if (optionalProduct.isEmpty()) {
			throw new RuntimeException("Product not found");
		}
		if (optionalUser.isEmpty()) {
			throw new RuntimeException("User not found");
		}

		User user = optionalUser.get();
		Product product = optionalProduct.get();
		Cart cart = user.getCart();
		List<Cart> cartsList = product.getCarts();
		if(cart==null) {
			return null;
		}
		cart.getProducts().remove(product);
		cartsList.remove(cart);
		
		if (cart.getProducts().isEmpty()) {
			user.setCart(null);
			productRepository.save(product);
			cartRepository.delete(cart);
			return null;
		}
		productRepository.save(product);
		cart = cartRepository.save(cart);
		return convertToDto(cart);
	}

	@Override
	public List<ProductDto> cartProducts(Long userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
		Cart cart = user.getCart();
		if (cart != null) {
			Set<Product> list = cart.getProducts();
			return list.stream().map(productService::convertToDto).collect(Collectors.toList());
		}
		
		return new ArrayList<>();
	}

}
