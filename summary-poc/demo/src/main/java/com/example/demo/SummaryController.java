package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SummaryController {
	
//	@Autowired
	RestTemplate restTemplate = new RestTemplate();
	
	@GetMapping("/summary")
	public List<String> getSummaryDetails() throws InterruptedException, ExecutionException {
		List<String> accountNumbers = new ArrayList<>();
		List<String> childAccounts = Arrays.asList("52189","4657");
		CompletableFuture.allOf(
				CompletableFuture.supplyAsync( () ->
				restTemplate.exchange( "http://localhost:8080/accountNumber/1",HttpMethod.GET, null, String.class))
				.thenAccept(accNum -> accountNumbers.add(accNum.getBody())))
				.join();
		
		return accountNumbers;
		
	}
	
	
	public String getAccontDetails(String accNumber) {
		ResponseEntity<String> response = restTemplate.exchange( "http://localhost:8080/accountNumber/"+accNumber,HttpMethod.GET, null, String.class);
		return response.getBody();
	}
	
	@PostMapping("accounts/tendigit")
	public List<String> tenDigitAccInfo(@RequestBody List<String> childAcc) throws InterruptedException{
		StopWatch st = new StopWatch();
		st.start();
		List<String> response = callToBackend(childAcc);
		st.stop();
		System.out.println("latency TotalTimeSeconds"+st.getTotalTimeMillis());
		return response;
	}
	
	public List<String> callToBackend(List<String> childAcc) throws InterruptedException {
		List<String> tenDigitAccountNums=null;
		SummaryController obj = new SummaryController();
	
		long start1 = System.currentTimeMillis();
//		tenDigitAccountNums = childAcc.parallelStream().map(accnum -> obj.getAccontDetails(accnum)).collect(Collectors.toList());
		childAcc.parallelStream().map(accnum -> obj.getAccontDetails(accnum)).collect(Collectors.toList());
		Thread.sleep(5);
		long end1 = System.currentTimeMillis();
        System.out.printf(Thread.currentThread().getName()+"The Normal operation took %s ms%n", end1 - start1);
//		
//		CompletableFuture.allOf(
//				
//				CompletableFuture.supplyAsync( () ->
//				restTemplate.exchange( "http://localhost:8080/accountNumber/1",HttpMethod.GET, null, String.class))
//				.thenAccept(accNum -> tenDigitAccountNums.add(accNum.getBody())))
//				.join();
		
//		new 
		 // Asynchronous execution
		long start = System.currentTimeMillis();
		List<CompletableFuture<String>> featureList = null;
        Executor executor = Executors.newFixedThreadPool(10);
        featureList = childAcc.stream().map( uniqueId -> CompletableFuture.supplyAsync( () ->
				obj.getAccontDetails(uniqueId),executor) )
		.collect(Collectors.toList())  ;
        tenDigitAccountNums = featureList.stream().map(CompletableFuture::join).collect(Collectors.toList());
        Thread.sleep(5);
        long end = System.currentTimeMillis();
        
        System.out.printf(Thread.currentThread().getName()+"The future operation took %s ms%n", end - start );
        
        
		return tenDigitAccountNums;
	
	}
	
	@GetMapping("/accountNumber/{id}")
	public String getAccountNumbers(@PathVariable String id) {
		long accountNum = generateID();
		return  String.valueOf(accountNum);
		
	}
	
	
	public static long generateID() { 
	    Random rnd = new Random();
	    char [] digits = new char[11];
	    digits[0] = (char) (rnd.nextInt(9) + '1');
	    for(int i=1; i<digits.length; i++) {
	        digits[i] = (char) (rnd.nextInt(10) + '0');
	    }
	    return Long.parseLong(new String(digits));
	}
}
