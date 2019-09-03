/**
 * 
 */
package com.qhc.bayern.service;

import org.springframework.http.HttpHeaders;

import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

import reactor.core.publisher.Mono;

/**
 * @author wang@dxc.com
 *
 */
public abstract class AbsFryeService {

	protected Builder getBuilder() {
		WebClient.Builder webClientBuilder = WebClient.builder()
				.defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json").defaultHeader("App-Locale", "chs")
				.defaultHeader(HttpHeaders.USER_AGENT,
						"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
		webClientBuilder.filter(logRequest());
		return webClientBuilder;
	}

	private ExchangeFilterFunction logRequest() {
		return (clientRequest, next) -> {
			System.out.println("Request: {} {}" + clientRequest.method() + clientRequest.url());
			clientRequest.headers()
					.forEach((name, values) -> values.forEach(value -> System.out.println("{}={}" + name + value)));
			return next.exchange(clientRequest);
		};
	}

	public void uploadFromJason(String server, String path, Object t) {

		WebClient webClient = getBuilder().baseUrl(server + path).build();
		System.out.println("webclient");
		ResponseSpec response = webClient.put().uri(path).retrieve();

		System.out.println(response);
	}
	
	public String get(String server, String path) {
		System.out.println(server);
		System.out.println(path);
		WebClient webClient = getBuilder().baseUrl(server + path).build();
		System.out.println("webclient");
		Mono<String> response = webClient.get().uri(path).retrieve().bodyToMono(String.class);
		return response.block();
	}

}
