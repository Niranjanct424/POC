package com.java.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.app.dto.ProductRequest;
import com.java.app.dto.UpdateProductRequest;
import com.java.app.entity.Product;
import com.java.app.exception.UserNotFoundExeption;
import com.java.app.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	ProductRepository repository;

	public Product saveProduct(ProductRequest product) {
		Product productData  = new Product();
		productData.setName(product.getName());
		productData.setPrice(product.getPrice());
		productData.setQuantity(product.getQuantity());
		productData.setEmail(product.getEmail());
		productData.setMobile(product.getMobile());
	
		return repository.save(productData);
	}

	public List<Product> saveProducts(List<ProductRequest> prodtsInput) {
		List<Product> products = new ArrayList<Product>();
		prodtsInput
		.stream()
		.forEach(prdt -> products.add(new Product(0, prdt.getName(), prdt.getQuantity(), prdt.getPrice(),prdt.getEmail(),prdt.getMobile())));
		
		return repository.saveAll(products);
	}

	public List<Product> getAllProducts() {
		return repository.findAll();
	}

//	public Optional<Product> getProductById(int id) {
//		return repository.findById(id);
//	}

	public Product getProductById(int id) throws UserNotFoundExeption {
		return repository.findById(id).orElseThrow( () -> {
			return new UserNotFoundExeption("User not found with id "+id);
		});
	}

	public Product getProductByName(String name) {
		return repository.findByName(name);
	}

	public String deleteProduct(int id) {
		repository.deleteById(id);
		return "Product removed successfully " + id;
	}

	public Product updateProduct(UpdateProductRequest product) {
		Product existingProduct = repository.findById(product.getId()).orElse(null);
		existingProduct.setId(product.getId());
		existingProduct.setName(product.getName());
		existingProduct.setPrice(product.getPrice());
		existingProduct.setQuantity(product.getQuantity());
		existingProduct.setEmail(product.getEmail());
		existingProduct.setMobile(product.getMobile());
		return repository.save(existingProduct);

	}

}
