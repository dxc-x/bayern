/**
 * 
 */
package com.qhc.bayern.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.qhc.bayern.controller.entity.Order;

import reactor.core.publisher.Mono;

/**
 * @author wang@dxc.com
 *
 */
@Service
public class OrderService {

	@Autowired
	ApplicationConfiguration configService;

	public void updateStatus() {
		WebClient webClient = WebClient.create();
		Mono<Order> mono = webClient.post().uri(configService.getFryeServer()+"/material/save").retrieve()
				.bodyToMono(Order.class);
		Mono<String> resp = WebClient.create().post().uri(configService.getFryeServer()+"/order/save")
				.contentType(MediaType.APPLICATION_PROBLEM_JSON_UTF8).body(BodyInserters.fromObject(new Order()))
				.retrieve().bodyToMono(String.class);
		System.out.println("updateStatus:" + resp.toString());

	}
}
