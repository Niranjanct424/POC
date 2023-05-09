package com.java.app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.meanbean.test.BeanTester;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.java.app.dto.ProductRequest;
import com.java.app.dto.UpdateProductRequest;
import com.java.app.entity.Product;
import com.java.app.exception.UserNotFoundExeption;
import com.java.app.repository.ProductRepository;
import com.java.app.service.ProductService;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

	@InjectMocks
	ProductService productService;

	@Mock
	ProductRepository productRepository;
	
	static Product product;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		product = new Product(41, "laptop", 50, 150000, "example@gmail.com", "0123456789");
	}
//	
//	@BeforeEach
//	 public void setup(){
//		
//	}

	@DisplayName("JUnit test for save Product")
	@Test
	void insertProductSeriveTest() {
		given(productRepository.save(sampleProduct())).willReturn(sampleProduct());
		productService.saveProduct(new ProductRequest("laptop", 50, 150000, "example@gmail.com", "0123456789"));
	}

	@DisplayName("JUnit test for insertBulkProducts")
	@Test
	void insertBulkProductsTest() {
		given(productRepository.saveAll(getProducts())).willReturn(getProducts());
		productService.saveProducts(getProductsRequestList());
	}

	@DisplayName("JUnit test to find Product by Id")
	@Test
	void findProductByIdSeriveTest() throws UserNotFoundExeption {
		given(productRepository.findById(41))
				.willReturn(Optional.of(new Product(41, "laptop", 50, 150000, "example@gmail.com", "0123456789")));
		productService.getProductById(41);
		
		
		assertThrows(UserNotFoundExeption.class, () -> {
			productService.getProductById(0);
		});

	}

	@DisplayName("JUnit test to find Product by Name")
	@Test
	void findProductByNameSeriveTest() throws UserNotFoundExeption {
		given(productRepository.findByName("laptop"))
				.willReturn(new Product(41, "laptop", 50, 150000, "example@gmail.com", "0123456789"));
		productService.getProductByName("laptop");
	}

	@DisplayName("JUnit test to find All Products ")
	@Test
	void findAllProductsSeriveTest() throws UserNotFoundExeption {
		given(productRepository.findAll()).willReturn(getProducts());
		productService.getAllProducts();
	}

	@DisplayName("JUnit test for update Product")
	@Test
	void updateProductSeriveTest() {
		given(productRepository.findById(17))
		.willReturn(Optional.of(new Product(17, "desktop", 850, 250000, "desktop@gmail.com", "1234567890")));
		given(productRepository.save(new Product(17, "desktop", 850, 250000, "desktop@gmail.com", "1234567890")))
				.willReturn(new Product(17, "desktop", 850, 250000, "desktop@gmail.com", "1234567890"));
		productService
				.updateProduct(new UpdateProductRequest(17, "desktop", 850, 250000, "desktop@gmail.com", "1234567890"));
	}
	
	@DisplayName("JUnit test for remove or delete Product")
	@Test
	void removeProductSeriveTest() {
		willDoNothing().given(productRepository).deleteById(17);
		// when -  action or the behaviour that we are going test
		productService.deleteProduct(17);
		// then - verify the output
        verify(productRepository, times(1)).deleteById(17);
		
	}

	public static Product sampleProduct() {
		return new Product(0, "laptop", 50, 150000, "example@gmail.com", "0123456789");
	}

	public static List<ProductRequest> getProductsRequestList() {
		List<ProductRequest> products = Arrays.asList(
				new ProductRequest("earpod", 50, 15000, "earpods@gmail.com", "0123456789"),
				new ProductRequest("mouse", 500, 750, "mouse@gmail.com", "0123456789"));
		return products;
	}

	public static List<Product> getProducts() {
		List<Product> products = Arrays.asList(new Product(0, "earpod", 50, 15000, "earpods@gmail.com", "0123456789"),
				new Product(0, "mouse", 500, 750, "mouse@gmail.com", "0123456789"));
		return products;
	}



}
