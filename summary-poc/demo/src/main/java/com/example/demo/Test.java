package com.example.demo;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Test {

	
	public List<String> callToBackend(List<String> childAcc) {
		List<String> tenDigitAccountNums=null;
		SummaryController obj = new SummaryController();
		long start1 = System.currentTimeMillis();
		tenDigitAccountNums = childAcc.parallelStream().map(accnum -> obj.getAccontDetails(accnum)).collect(Collectors.toList());
		long end1 = System.currentTimeMillis();
        System.out.printf("The Normal operation took %s ms%n", end1 - start1);
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
		
        long end = System.currentTimeMillis();
        System.out.printf("The future operation took %s ms%n", end - start);
        
        
		return tenDigitAccountNums;
	
	}
	
	public static void main(String[] args) {
		Test t = new Test();
		t.callToBackend(Arrays.asList("63887","15627"));
	}

}
