package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Xcontroller {
	
	@GetMapping("/hello")
	public Summary hello() {
		
		Map<String, InvoiceDetails[]> map = new HashMap<String, InvoiceDetails[]>();
		InvoiceDetails[] parentArr = new InvoiceDetails[2];
		InvoiceDetails parent  = new InvoiceDetails();
		
		parent.setDate("112244");
		parent.setLocation("asdf/asdf/adf/samle.pdf");
		parentArr[0] = parent;
		parent.setDate("112244");
		parent.setLocation("asdf/asdf/adf/samle.pdf");
		parentArr[1] = parent;
		
		InvoiceDetails[] childArr = new InvoiceDetails[2];
		InvoiceDetails parent1  = new InvoiceDetails();
		
		parent1.setDate("112244");
		parent1.setLocation("asdf/asdf/adf/samle.pdf");
		childArr[0] = parent1;
		parent1.setDate("112244");
		parent1.setLocation("asdf/asdf/adf/samle.pdf");
		childArr[1] = parent1;
		
		
		
		//response 
		map.put("parent", parentArr);
		map.put("child", childArr);
		Summary summary = new Summary();
		summary.setSummary(map);
		//response child
//		Request request = new Request();
//		request.setSummary(summary);
		

		
		return summary;
	}
	
	

}
