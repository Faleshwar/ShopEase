package com.shopease.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopease.dto.ProductDto;
import com.shopease.model.Category;
import com.shopease.model.Product;
import com.shopease.repository.CategoryRepository;
import com.shopease.repository.ProductRepository;
import com.shopease.service.ProductService;


@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	private Product convertToEntity(ProductDto productDto) {
		return new Product(productDto.getId(), productDto.getName(), productDto.getImage(), productDto.getDescription(), productDto.getPrice(), productDto.getStock(),productDto.getCategory());
	}
	
	private ProductDto convertToDto(Product product) {
		return new ProductDto(product.getId(), product.getName(), product.getImage(), product.getDescription(),product.getPrice(),product.getStock(), product.getCategory());
	}

	@Override
	public List<ProductDto> getAllProduct() {
	
		List<Product> products = productRepository.findAll();
		return 	products.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	@Override
	public ProductDto getProductById(Long productId) throws Exception {
		Optional<Product> optionalProduct = productRepository.findById(productId);
		if(!optionalProduct.isPresent()) {
			throw new RuntimeException("Product not found");
		}
		return convertToDto(optionalProduct.get());
	}

	@Override
	public ProductDto deleteProduct(Long productId) throws Exception {
		Optional<Product> optionalProduct = productRepository.findById(productId);
		if(!optionalProduct.isPresent()) {
			throw new RuntimeException("Product not found");
		}
		Product product = optionalProduct.get();
		ProductDto productDto = convertToDto(product);
		productRepository.delete(product);
		return productDto;
	}

	@Override
	public ProductDto updateProduct(ProductDto productDto, Long productId) throws Exception {
		
		Optional<Product> productOpt = productRepository.findById(productId);
		if(!productOpt.isPresent()) {
			throw new Exception("Product not found");
		}
		
		Product product = productOpt.get();
		
		if(productDto.getName() != null) {
			product.setName(productDto.getName());
		}
		if(productDto.getDescription() != null) {
			product.setDescription(productDto.getDescription());
		}
		
		if(productDto.getImage() != null) {
			product.setImage(productDto.getImage());
		}
		
		if(productDto.getPrice() != null) {
			product.setPrice(productDto.getPrice());
		}
		if(productDto.getStock() != null) {
			product.setStock(productDto.getStock());
		}
		
		if(productDto.getCategory() != null) {
			product.setCategory(productDto.getCategory());
		}
		
		productRepository.save(product);
		
		
		return convertToDto(product);
	}

	@Override
	public ProductDto addProduct(ProductDto productDto) {
		Product product = convertToEntity(productDto);
		Category category = categoryRepository.findByName(productDto.getCategory().getName());
		product.setCategory(category);
		Product savedProduct = productRepository.save(product);
		return convertToDto(savedProduct);
	}
	
	

}
