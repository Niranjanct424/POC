package com.java.app.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.app.dto.ProductRequest;
import com.java.app.dto.UpdateProductRequest;
import com.java.app.entity.Product;
import com.java.app.service.ProductService;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductService service;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void findProductByIdControllerTest() throws Exception {
		when(service.getProductById(41)).thenReturn(sampleProduct());
		this.mockMvc.perform(get("/productById/41")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(sampleProduct())));
	}

	@Test
	public void findAllProductsTest() throws Exception {
		when(service.getAllProducts()).thenReturn(getProducts());
		this.mockMvc.perform(get("/products")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(getProducts())))
				.andExpect(jsonPath("$.length()", is(2)));
	}

	@Test
	public void findProductByNameControllerTest() throws Exception {
		when(service.getProductByName("laptop")).thenReturn(sampleProduct());
		this.mockMvc.perform(get("/productByName/laptop")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(sampleProduct())));
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
				.andExpect(jsonPath("$.id", is(17))).andExpect(content().contentType(MediaType.APPLICATION_JSON));

	}

	@Test
	public void addBulkProductsControllerTest() throws Exception {

		when(service.saveProducts(getProductsRequestList())).thenReturn(getProducts());
		String json = objectMapper.writeValueAsString(getProductsRequestList());
		mockMvc.perform(post("/addProducts").contentType(MediaType.APPLICATION_JSON).content(json)
				.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isCreated())
				.andExpect(content().json(objectMapper.writeValueAsString(getProducts())))
				.andExpect(jsonPath("$[0].name").value("earpod")).andExpect(jsonPath("$[0].price").value(15000))
				.andExpect(jsonPath("$[1].name").value("mouse")).andExpect(jsonPath("$[1].price").value(750))
				.andExpect(jsonPath("$[1].id", is(72))).andExpect(content().contentType(MediaType.APPLICATION_JSON));

	}

	@Test
	public void updateProductControllerTest() throws Exception {
		Product updatedPrdt = new Product(17, "desktop", 850, 250000, "desktop@gmail.com", "1234567890");
		UpdateProductRequest updatePrdtReq = new UpdateProductRequest(17, "desktop", 850, 250000, "desktop@gmail.com",
				"1234567890");

		when(service.updateProduct(updatePrdtReq)).thenReturn(updatedPrdt);
		String json = objectMapper.writeValueAsString(updatePrdtReq);
		mockMvc.perform(put("/updateProduct").contentType(MediaType.APPLICATION_JSON).content(json)
				.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().json(objectMapper.writeValueAsString(updatedPrdt)))
				.andExpect(jsonPath("$.name").value("desktop")).andExpect(jsonPath("$.price").value(250000))
				.andExpect(jsonPath("$.id", is(17))).andExpect(content().contentType(MediaType.APPLICATION_JSON));

	}

	@Test
	public void deleteProductByIdControllerTest() throws Exception {
		int id = 41;
		String response = "Prodduct deleted successfully";
		when(service.deleteProduct(id)).thenReturn(response);
		this.mockMvc.perform(delete("/deleteProduct/41")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString(response)));
	} 

	public static Product sampleProduct() {
		return new Product(41, "laptop", 50, 150000, "example@gmail.com", "0123456789");
	}

	public static List<Product> getProducts() {
		List<Product> products = Arrays.asList(new Product(71, "earpod", 50, 15000, "earpods@gmail.com", "0123456789"),
				new Product(72, "mouse", 500, 750, "mouse@gmail.com", "0123456789"));
		return products;
	}

	public static List<ProductRequest> getProductsRequestList() {
		List<ProductRequest> products = Arrays.asList(
				new ProductRequest("earpod", 50, 15000, "earpods@gmail.com", "0123456789"),
				new ProductRequest("mouse", 500, 750, "mouse@gmail.com", "0123456789"));
		return products;
	}

}
