/**
 * 
 */
package com.qhc.bayern.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.qhc.bayern.controller.entity.Order;
import com.qhc.bayern.controller.entity.SapCreationOrder;

import reactor.core.publisher.Mono;

/**
 * @author wang@dxc.com
 *
 */
@Service
public class OrderService {
	private static Logger log = LoggerFactory.getLogger(OrderService.class);

	public void updateStatus() {
//		WebClient webClient = WebClient.create();
//		Mono<Order> mono = webClient.post().uri(configService.getFryeServer()+"/material/save").retrieve()
//				.bodyToMono(Order.class);
//		Mono<String> resp = WebClient.create().post().uri(configService.getFryeServer()+"/order/save")
//				.contentType(MediaType.APPLICATION_PROBLEM_JSON_UTF8).body(BodyInserters.fromObject(new Order()))
//				.retrieve().bodyToMono(String.class);
//		System.out.println("updateStatus:" + resp.toString());

	}
	
	/**
	 *   获取销售订单数据并同步SAP
	 *  TODO : SAP接口未提供
	 * @param SapCreationOrder 销售订单详情
	 */
	public String orderCreationForSAP(SapCreationOrder sapCreationOrder) {
		
		String res = null;
	
		//1.ͬ同步SAP开单 没有数据 先注释
		//String sapStr = JSONObject.toJSONString(sapCreationOrder);
		try {
			//TODO:  SAP接口未提供 未做测试 
			//没有数据先注释
			//res = HttpUtil.postbody(orderCreationUrl, sapStr);
			
		} catch (Exception e) {
			log.error("ͬ同步SAP异常==>",e);
			throw new RuntimeException("ͬ同步SAP异常");
		}
		
		//2. 处理返回结果
		log.info("SAP返回结果==>"+res);
		
//		return res;
		
		return "SUCCESS";
		
	}
}
