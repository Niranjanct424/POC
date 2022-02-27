package com.fedex.eccp.contoller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
@RequestMapping("/sample")
public class MessageRestController {

	@Value("${message:Config Server is not working. Please check...}")
	private String msg;
	
	@Value("${server.port}")
	private String port;

	@GetMapping("/msg")
	public String getMsg() {
		return this.msg;
	}
	
	@GetMapping("/greet")
	public String getGreet() {
		return "greet service running in port "+port;
	}
}