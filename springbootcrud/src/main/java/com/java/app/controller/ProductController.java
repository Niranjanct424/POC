package com.java.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.java.app.dto.ProductRequest;
import com.java.app.dto.UpdateProductRequest;
import com.java.app.entity.Product;
import com.java.app.exception.UserNotFoundExeption;
import com.java.app.service.ProductService;

@Validated
@RestController
public class ProductController {

	@Autowired
	ProductService productService;

	@PostMapping("/addProduct")
	public ResponseEntity<Product> addProduct(@RequestBody @Valid ProductRequest product) {
		
		return new ResponseEntity<Product>(productService.saveProduct(product), HttpStatus.CREATED);
	}

	@PostMapping("/addProducts")
	public ResponseEntity<List<Product>> addProducts(@RequestBody @Valid List<ProductRequest> products) {
		return new ResponseEntity<List<Product>>(productService.saveProducts(products), HttpStatus.CREATED);
	}

	@GetMapping("/products")
	public ResponseEntity<List<Product>> findAllProducts() {
		return new  ResponseEntity<List<Product>>(productService.getAllProducts(), HttpStatus.OK);
	}

	@GetMapping("/productById/{id}")
	public ResponseEntity<Product> findProductById(@PathVariable int id) throws UserNotFoundExeption {
		return new ResponseEntity<>(productService.getProductById(id),HttpStatus.OK);
	}
	

	@GetMapping("/productByName/{name}")
	public ResponseEntity<Product> findProductByName(@PathVariable String name) {
		return new ResponseEntity<>(productService.getProductByName(name),HttpStatus.OK);
	}

	@PutMapping("/updateProduct")
	public ResponseEntity<Product> updateProduct(@RequestBody @Valid UpdateProductRequest product) {
		return new ResponseEntity<>(productService.updateProduct(product),HttpStatus.OK);
	}

	@DeleteMapping("/deleteProduct/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable int id) {
		return new ResponseEntity<String>(productService.deleteProduct(id), HttpStatus.OK);
	}
}
