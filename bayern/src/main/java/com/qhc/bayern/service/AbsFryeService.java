/**
 * 
 */
package com.qhc.bayern.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.qhc.bayern.controller.entity.Order;

import reactor.core.publisher.Mono;

/**
 * @author wang@dxc.com
 *
 */
public abstract class AbsFryeService {
	@Autowired
	WebClient webClient;
	
	public final static String SALES_OFFICES = "/material/save";
	
	public void upload() {
//		String server = configService.getFryeServer();
//		Mono<Order> mono = webClient.post().uri(+"/material/save").retrieve()
//				.bodyToMono(Order.class);
//		Mono<String> resp = WebClient.create().post().uri(configService.getFryeServer()+"/order/save")
//				.contentType(MediaType.APPLICATION_PROBLEM_JSON_UTF8).body(BodyInserters.fromObject(new Order()))
//				.retrieve().bodyToMono(String.class);
//		System.out.println("updateStatus:" + resp.toString());

	}

}
