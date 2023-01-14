package com.example.demo;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.core.Response;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AsyncRestClient {

    private final Client client = ClientBuilder.newClient();

    public void makeAsyncCalls(List<String> requests) {
        List<Future<Response>> futures = requests.stream()
            .map(request -> client.target("http://localhost:8080/hello")
                .request()
                .async()
                .post(Entity.json(request), new InvocationCallback<Response>() {
                    @Override
                    public void completed(Response response) {
                        // Handle successful response
                    	System.out.println(response.getEntity());
                    }

                    @Override
                    public void failed(Throwable throwable) {
                        // Handle failure
                    }
                })
            ).collect(Collectors.toList());

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).join();
    }
    
    @GetMapping("/simple")
    public void getData() {
    List<String> requests = Arrays.asList("1234","76876");
    	makeAsyncCalls(requests);
    }
}
