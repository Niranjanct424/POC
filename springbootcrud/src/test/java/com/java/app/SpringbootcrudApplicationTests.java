//package com.java.app;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import java.net.URI;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import com.java.app.entity.Product;
//
//@SpringBootTest
//class SpringbootcrudApplicationTests {
//
////	@Autowired
////	private TestRestTemplate restTemplate;
////
////	@Test
////	void contextLoads() {
////	}
////
////	@Test
////	@DisplayName("/findProductById rest api test ")
////	void testMessage() {
////
//////		String user = "Peter";
////		Product expectedProduct = new Product(41, "laptop", 50, 150000, "example@gmail.com", "01234567891");
//////		URI targetUrl = UriComponentsBuilder.fromUriString("/productById/{id}").queryParam("user", user).build().encode().toUri();
////		URI targetUrl = UriComponentsBuilder.fromUriString("/productById/41").build().encode().toUri();
////
////		Product responseProduct = this.restTemplate.getForObject(targetUrl, Product.class);
////		assertEquals(expectedProduct, responseProduct);
////	}
//
//}
