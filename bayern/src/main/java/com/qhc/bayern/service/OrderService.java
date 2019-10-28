/**
 * 
 */
package com.qhc.bayern.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qhc.bayern.controller.entity.Order;
import com.qhc.bayern.controller.entity.Parameter;
import com.qhc.bayern.controller.entity.PaymentPlan;
import com.qhc.bayern.controller.entity.sap.SapCreationOrder;
import com.qhc.bayern.util.HttpUtil;

import reactor.core.publisher.Mono;

/**
 * @author wang@dxc.com
 *
 */
@Service
public class OrderService {
	private static Logger log = LoggerFactory.getLogger(OrderService.class);
	
	@Autowired
	private FryeService<List<?>> frye;
	
	@Value("${sap.paymentplan.addr}")
	String paymentplanUrlStr;

	@Value("${sap.sapCreateOrder.addr}")
	private String orderCreationUrl;
	
	@Value("${sap.sapChangeOrder.addr}")
	private String orderChangeUrl;

	private final static String PAYMENT = "order/paymentPlan";
	
	public void savePaymentPlan(List<PaymentPlan> PaymentPlan) throws Exception {
		frye.putJason(PAYMENT, PaymentPlan);
	}
	
	public List<PaymentPlan> getPaymentFromSAP(){
		List<PaymentPlan> lp = new ArrayList<PaymentPlan>();
		try {
			//接口请求参数
			Parameter parameter2 = new Parameter();
			parameter2.setKey("LANGU");
			parameter2.setValue("1");
			List<Parameter> parList = new ArrayList<Parameter>();
			parList.add(parameter2);
			String paymentplanParam = JSONObject.toJSONString(parList);
			//发送请求获取数据
			String bb = HttpUtil.postbody(paymentplanUrlStr, paymentplanParam);
			JSONObject parseObject = JSONObject.parseObject(bb);
			Object message = parseObject.get("message");
			Object plData = parseObject.get("data_bl");
			Object ptData = parseObject.get("data_pt");
			JSONArray plDataArray = JSONArray.parseArray(plData.toString());
			JSONArray ptDataArray = JSONArray.parseArray(ptData.toString());
			for (int i = 0; i < ptDataArray.size();i++) { 
				JSONObject obj = (JSONObject)ptDataArray.get(i);
				if("".equals(obj.getString("text1"))) {
					System.out.println("name不能为空");
					continue;
				}else {
					PaymentPlan pm = new PaymentPlan();
					pm.setCode(obj.getString("zterm"));
					pm.setName(obj.getString("text1"));
					pm.setPaymentTerm(true);
					lp.add(pm);
				}
			}
			for (int i = 0; i < plDataArray.size();i++) { 
				JSONObject obj = (JSONObject)plDataArray.get(i);
				if("".equals(obj.getString("tebez"))) {
					System.out.println("name不能为空");
					continue;
				}else {
					PaymentPlan pm = new PaymentPlan();
					pm.setCode(obj.getString("tetbe"));
					pm.setName(obj.getString("tebez"));
					pm.setPaymentTerm(false);
					lp.add(pm);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lp;
	}

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
		String sapStr = JSONObject.toJSONString(sapCreationOrder);
		log.info("Order Creation Data: {}", sapStr);
		try {
			//没有数据先注释
			res = HttpUtil.postbody(orderCreationUrl, sapStr);
			
		} catch (Exception e) {
			log.error("ͬ同步SAP异常==>",e);
			throw new RuntimeException("ͬ同步SAP异常");
		}
		
		//2. 处理返回结果
		log.info("SAP返回结果==>"+res);
		
		return "SUCCESS";
		
	}
}
