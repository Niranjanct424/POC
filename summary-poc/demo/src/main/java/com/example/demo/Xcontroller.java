package com.example.demo;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Xcontroller {
	RestTemplate restTemplate = new RestTemplate();
	
	class accNumbers {
		String accountNumber;
		String uniqueId;
		public String getAccountNumber() {
			return accountNumber;
		}
		public void setAccountNumber(String accountNumber) {
			this.accountNumber = accountNumber;
		}
		public String getUniqueId() {
			return uniqueId;
		}
		public void setUniqueId(String uniqueId) {
			this.uniqueId = uniqueId;
		}
		
		
	}

	@GetMapping("/poc")
	public Summary pocResponse() {
		List<accNumbers> list = new ArrayList<Xcontroller.accNumbers>();
		accNumbers accNum = new accNumbers();
		accNum.setAccountNumber("12345");
		accNum.setUniqueId("12");
		list.add(accNum);
		accNumbers accNum1 = new accNumbers();
		accNum1.setAccountNumber("6789");
		accNum1.setUniqueId("89");
		list.add(accNum);
		
		
		Random rand = new Random();

		// nextInt as provided by Random is exclusive of the top value so you need to
		// add 1

		int randomNum = rand.nextInt();
		Summary summaryResponse = new Summary();
		List<Summary> summaries = new ArrayList<>();
		for (int i = 0; i < 2; i++) {
			CompletableFuture.allOf(CompletableFuture.supplyAsync(
					() -> restTemplate.exchange("http://localhost:8080/hello", HttpMethod.GET, null, Summary.class))
					.thenAccept(response -> summaries.add(response.getBody()))).join();
		}

		// converting list of map to map
//		finalSummary.setSummary(summaries.stream().flatMap(map -> map.getSummary().entrySet().stream())
//				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
		List<String> resObjs = new ArrayList<String>();
		
		Map<String, InvoiceDetails[]> finalSummary = summaries.stream()
				.flatMap(map -> map.getSummary().entrySet().stream())
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
		Map<String, InvoiceDetails[]> map = new HashMap<String, InvoiceDetails[]>();
		finalSummary.entrySet().forEach(entry -> {
			if (entry.getKey().startsWith("pare")) {
				map.put("newparent" + getRandomNumber()+getRandomNumber(), entry.getValue());
			} else {
//				Xcontroller.accNumbers()
				
				map.put(entry.getKey(), entry.getValue());
			}
		});
		summaryResponse.setSummary(map);
		return summaryResponse;
	}
	
	public String getAccountNum(List<accNumbers> list, String accNum) {
		return list.stream()
	        .filter(o -> o.getAccountNumber().equals(accNum))
	        .map(uniqueId -> uniqueId.getUniqueId())
	        .findFirst()
	        .orElse(null);
	}
	


	
	


	@GetMapping("/hello")
	public Summary hello() {

		Map<String, InvoiceDetails[]> map = new HashMap<String, InvoiceDetails[]>();
		InvoiceDetails[] parentArr = new InvoiceDetails[2];
		InvoiceDetails parent = new InvoiceDetails();

		parent.setDate("112244");
		parent.setLocation("asdf/asdf/adf/samle.pdf");
		parentArr[0] = parent;
		parent.setDate("112244");
		parent.setLocation("asdf/asdf/adf/samle.pdf");
		parentArr[1] = parent;

		InvoiceDetails[] childArr = new InvoiceDetails[2];
		InvoiceDetails parent1 = new InvoiceDetails();

		parent1.setDate("112244");
		parent1.setLocation("asdf/asdf/adf/samle.pdf");
		childArr[0] = parent1;
		parent1.setDate("112244");
		parent1.setLocation("asdf/asdf/adf/samle.pdf");
		childArr[1] = parent1;

		// response


		map.put("parent" + getRandomNumber(), parentArr);
		map.put("child" + getRandomNumber(), childArr);
		Summary summary = new Summary();
		summary.setSummary(map);
		// response child
//		Request request = new Request();
//		request.setSummary(summary);

		return summary;
	}
	
	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(10 - 1 + 1) + 1;
	}
	


}
