/**
 * 
 */
package com.qhc.bayern.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.Builder;

import com.qhc.bayern.config.ApplicationConfig;
import com.qhc.bayern.service.exception.ExternalServerInternalException;
import com.qhc.bayern.service.exception.URLNotFoundException;

import reactor.core.publisher.Mono;

/**
 * @author wang@dxc.com
 * @param <T>
 *
 */
@Service
public class FryeService<T> {
	
	@Autowired
	ApplicationConfig config;

	private WebClient webClient;

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
			System.out.println("Request: " + clientRequest.method() +" : "+ clientRequest.url());
			clientRequest.headers()
					.forEach((name, values) -> values.forEach(value -> System.out.println("{}={}" + name + value)));
			return next.exchange(clientRequest);
		};
	}

	public void putJason(String path, T params) {

		webClient = getBuilder().baseUrl(config.getFryeURL()).build();
		Mono<String> response = webClient.put().uri(path).contentType(MediaType.APPLICATION_JSON).bodyValue(params)
				.retrieve()
				.onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(new URLNotFoundException()))
				.onStatus(HttpStatus::is5xxServerError,
						clientResponse -> Mono.error(new ExternalServerInternalException()))
				.bodyToMono(String.class);
		response.block();
	}

	public void postJason(String path, T params) {
		
		webClient = getBuilder().baseUrl(config.getFryeURL()).build();
		Mono<String> response = webClient.post().uri(path).contentType(MediaType.APPLICATION_JSON).bodyValue(params)
				.retrieve()
				.onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(new URLNotFoundException()))
				.onStatus(HttpStatus::is5xxServerError,
						clientResponse -> Mono.error(new ExternalServerInternalException()))
				.bodyToMono(String.class);
		response.block();
	}
	/**
	 * get last updated date by the business code defined in system
	 */
	public Date getLastUpdatedDate(String path) {
		
		webClient = getBuilder().baseUrl(config.getFryeURL()).build();
		
		Mono<Date> response = webClient.get().uri(path)
				.retrieve()
				.onStatus(HttpStatus::is4xxClientError, clientResponse -> Mono.error(new URLNotFoundException()))
				.onStatus(HttpStatus::is5xxServerError,
						clientResponse -> Mono.error(new ExternalServerInternalException()))
				.bodyToMono(Date.class);
		return response.block();
	}

}