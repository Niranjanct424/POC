package com.java.app.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

	@NotNull(message = "username shouldn't be null")
	private String name;
	@Min(value=1, message = "min quantity can't be less than 1" )
	private int quantity;
	private double price;
	@NotNull(message = "email shouldn't be null")
	@Email(message = "invalid email address")
	private String email;
	@Pattern(regexp = "^\\d{10}$", message = "invalid mobile number entered ")
	@NotBlank(message = "mobile shouldn't be empty")
	private String mobile;
	
	
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public int getQuantity() {
//		return quantity;
//	}
//	public void setQuantity(int quantity) {
//		this.quantity = quantity;
//	}
//	public double getPrice() {
//		return price;
//	}
//	public void setPrice(double price) {
//		this.price = price;
//	}
//	public String getEmail() {
//		return email;
//	}
//	public void setEmail(String email) {
//		this.email = email;
//	}
//	public String getMobile() {
//		return mobile;
//	}
//	public void setMobile(String mobile) {
//		this.mobile = mobile;
//	}
//	public ProductRequest() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//	public ProductRequest(@NotNull(message = "username shouldn't be null") String name,
//			@Min(value = 1, message = "msg1") int quantity, double price,
//			@Email(message = "invalid email address") String email,
//			@Pattern(regexp = "^\\d{10}$", message = "invalid mobile number entered ") String mobile) {
//		super();
//		this.name = name;
//		this.quantity = quantity;
//		this.price = price;
//		this.email = email;
//		this.mobile = mobile;
//	}
	
	

}
