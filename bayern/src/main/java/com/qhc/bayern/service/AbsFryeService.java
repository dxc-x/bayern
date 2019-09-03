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
	//@Autowired(required=false)
	protected WebClient webClient;

	public void upLoadByJason(String server,String path,Object t) {
		
		WebClient webClient = WebClient.create(path);

		webClient.post().uri(path).syncBody(t).retrieve();
		
	}
	
}
