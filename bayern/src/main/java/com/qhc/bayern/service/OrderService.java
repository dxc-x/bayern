/**
 * 
 */
package com.qhc.bayern.service;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.qhc.bayern.controller.entity.Order;

import reactor.core.publisher.Mono;

/**
 * @author wwang67
 *
 */
@Service
public class OrderService {
	
	public void updateStatus() {
		WebClient webClient = WebClient.create();
		Mono<Order> mono = webClient.post().uri("http://localhost:8801/order/save").retrieve().bodyToMono(Order.class);
		Mono<String> resp = WebClient.create().post()
                .uri("http://localhost:8801/order/save")
                .contentType(MediaType.APPLICATION_PROBLEM_JSON_UTF8)
                .body(BodyInserters.fromObject(new Order()))
                .retrieve().bodyToMono(String.class);
       
		
	}
}
