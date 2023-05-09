package com.java.app.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductRequest {
	
	private int id;
	@NotNull(message = "username shouldn't be null")
	private String name;
	@Min(value=1, message = "msg1" )
	private int quantity;
	private double price;
	@Email(message = "invalid email address")
	private String email;
	@Pattern(regexp = "^\\d{10}$", message = "invalid mobile number entered ")
	private String mobile;
	

}
