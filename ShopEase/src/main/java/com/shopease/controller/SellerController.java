package com.shopease.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.shopease.dto.ProductDto;
import com.shopease.dto.UserDto;
import com.shopease.security.service.JwtService;
import com.shopease.service.ProductService;
import com.shopease.service.UserService;

@RestController
@RequestMapping("/seller")
public class SellerController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/product/add")
	public ResponseEntity<ProductDto> addProduct(@RequestHeader("Authorization") String auth,@RequestBody ProductDto productDto) throws Exception {
		String username = jwtService.getUsername(auth.substring(7));
		Long userId = userService.getUserByUsername(username).getId();
		ProductDto addeProductDto = productService.addProduct(productDto, userId);
		return new ResponseEntity<>(addeProductDto, HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/file/upload")
	public ResponseEntity<String> uploadImage(@RequestParam MultipartFile file) throws IOException{
		String fileName = UUID.randomUUID().toString()+"_"+file.getOriginalFilename();
		Path filePath = Paths.get("src/main/resources/static/upload/"+fileName);
		Files.write(filePath, file.getBytes());
		String fileUrl = "http://localhost:8080/upload/"+fileName;
		return ResponseEntity.ok(fileUrl);
	}
	
	@GetMapping("/product/all")
	public ResponseEntity<List<ProductDto>> getAllProductsOfSeller(@RequestHeader("Authorization") String auth) throws Exception{
		String token = auth.substring(7);
		String username= jwtService.getUsername(token);
		UserDto userDto = userService.getUserByUsername(username);
		List<ProductDto> productDtos = productService.getAllProductsOfSeller(userDto);
		return ResponseEntity.ok(productDtos);
	}

}
