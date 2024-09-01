package com.shopease.component;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.shopease.model.Cart;
import com.shopease.model.User;
import com.shopease.repository.CartRepository;
import com.shopease.repository.UserRepository;

@Component
public class DataLoader implements CommandLineRunner{
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
//		Cart cart = new Cart();
//		cart.setCreatedAt(LocalDateTime.now());
//		
//		Optional<User> optionalUser = userRepository.findById(3L);
//		User user = null;
//		if(optionalUser.isPresent()) {
//			user = optionalUser.get();
//		}
//		
//		user.setCart(cart);
//		cart.setUser(user);
//		userRepository.save(user);
	}

}
