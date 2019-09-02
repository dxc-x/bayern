package com.qhc.bayern.service;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class ConfirugrableService {
	
	public void doRestClient() {
		
		//WebClient webClient = WebClient.create();
		//Mono<User> mono = webClient.get().uri("http://localhost:8081/user/1").retrieve().bodyToMono(User.class);
	}

}
