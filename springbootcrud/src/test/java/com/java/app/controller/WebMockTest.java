package com.java.app.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.app.dto.ProductRequest;
import com.java.app.entity.Product;
import com.java.app.service.ProductService;

@WebMvcTest(ProductController.class)
public class WebMockTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductService service;

	@Autowired
	private ObjectMapper objectMapper;


	@Test
	public void findProductByIdControllerTest() throws Exception {
		Product expectedProduct = new Product(41, "laptop", 50, 150000, "example@gmail.com", "0123456789");
		when(service.getProductById(41)).thenReturn(expectedProduct);
		this.mockMvc.perform(get("/productById/41")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(expectedProduct)));
	}

	@Test
	public void createProductControllerTest() throws Exception {
		ProductRequest inputProduct = new ProductRequest("desktop", 850, 170000, "desktop@gmail.com", "1234567890");
		Product expectedPrdt = new Product(17, "desktop", 850, 170000, "desktop@gmail.com", "1234567890");
		when(service.saveProduct(inputProduct)).thenReturn(expectedPrdt);
		String json = objectMapper.writeValueAsString(inputProduct);
		mockMvc.perform(post("/addProduct").contentType(MediaType.APPLICATION_JSON).content(json)
				.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isCreated())
				.andExpect(content().json(objectMapper.writeValueAsString(expectedPrdt)))
				.andExpect(jsonPath("$.name").value("desktop")).andExpect(jsonPath("$.price").value(170000))
				.andExpect(jsonPath("$.id", is(17)))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));

	}

}