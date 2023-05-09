package com.java.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.app.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	Product findByName(String name);
	
}
