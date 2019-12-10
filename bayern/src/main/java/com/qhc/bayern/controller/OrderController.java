/**
 * 
 */
package com.qhc.bayern.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.qhc.bayern.config.ApplicationConfig;
import com.qhc.bayern.controller.entity.Form;
import com.qhc.bayern.controller.entity.Order;
import com.qhc.bayern.controller.entity.PaymentPlan;
import com.qhc.bayern.controller.entity.sap.SapCreationOrder;
import com.qhc.bayern.service.FryeService;
import com.qhc.bayern.service.OrderService;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author wang@dxc.com
 *
 */
@RestController
@Api(value = "Order Management in Bayern")
@RequestMapping("order")
public class OrderController {
	
	  private static Logger log = LoggerFactory.getLogger(OrderController.class);
	  
	  @Autowired
	  ApplicationConfig config;
	  
	  @Autowired
	  private OrderService orderService;
	  
	  @Autowired
	  FryeService fryeService;
	
	  @ApiOperation(value="push a new order to SAP")
	  @PostMapping(value = "new",produces = "application/json;charset=UTF-8")
	  @ResponseStatus(HttpStatus.OK)
	  public Order create(@RequestBody(required=true) @Valid Order order,@RequestBody(required=true) @Valid Form form) throws Exception
	  {
		 return order;
	  }
	  @ApiOperation(value="append a form to a order in SAP")
	  @PostMapping(value = "append",produces = "application/json;charset=UTF-8")
	  @ResponseStatus(HttpStatus.OK)
	  public Form appendForm(@RequestParam(value="orderCode",required=true) String code,@RequestBody @Valid Form form) throws Exception
	  {
		 return form;
	  }
	  @ApiOperation(value="update the order in SAP")
	  @PostMapping(value = "update",produces = "application/json;charset=UTF-8")
	  @ResponseStatus(HttpStatus.OK)
	  public Order update(@RequestBody(required=true) @Valid Order order) throws Exception
	  {
		 return order;
	  }
	  @ApiOperation(value="notify the order status from SAP")
	  @PostMapping(value = "updateStatus",produces = "application/json;charset=UTF-8")
	  @ResponseStatus(HttpStatus.OK)
	  public int notifyStatus(@RequestParam(value="orderCode",required=true) String code,@RequestParam int status) throws Exception
	  {
		 return status;
	  }
	  
	  @ApiOperation(value="get config about frye server url")
	  @GetMapping(value = "fryeUrl")
	  @ResponseStatus(HttpStatus.OK)
	  public String getFryeServer() throws Exception
	  {
		 
		 return config.getFryeServer();
	  }
	  //同步SAP结算方式数据到销售工具
	  @ApiOperation(value = "retrieve PaymentPlan from SAP to DB")
	  @GetMapping(value = "paymentPlan")
	  @ResponseStatus(HttpStatus.OK)
	  public void getPaymentPlan() throws Exception {
		  List<PaymentPlan> lp = orderService.getPaymentFromSAP();
		  orderService.savePaymentPlan(lp);
	  }
	  
	  /**
	      *组装销售订单数据并同步SAP
	   * @param order 
	   * @param form
	   * @return
	   * @throws Exception
	   * TODO: 具体DTO未定义 
	   * 
	   */
	  @ApiOperation(value="push a new order to SAP")
	  @PostMapping(value = "create/sapOrder",produces = "application/json;charset=UTF-8")
	  @ResponseStatus(HttpStatus.OK)
	  public String orderCreationForSap(@RequestBody(required=true) @Valid SapCreationOrder sapCreationOrder) throws Exception
	  {
		  
		    log.info("订单数据==>"+JSONObject.toJSONString(sapCreationOrder));
		    // 获取流水号: sequenceNumber
		  	String sapRes = orderService.orderCreationForSAP(sapCreationOrder);
		  	log.info("SAP同步开单结果==>"+sapRes);
		  	
		  	return sapRes;
		  
	  }
	    
	    /**
	     * 订单BPM回调接口
     * <li>sequenceNumber</li>
     * <li>status</li>
     * <li>bodyDiscount</li>
     * <li>unitDiscount</li>
	     * 
	     * @return
	     */
		@ApiOperation(value="查询订单类型", notes="查询订单类型")
		@PutMapping(value = "callback")
		@ResponseBody
		public boolean bpmCallback(@RequestBody Map data) {
//			String status = String.valueOf(data.get("status"));
//			String sequenceNumber = String.valueOf(data.get("sequenceNumber"));
//			Double bodyDiscount = Double.valueOf(String.valueOf(data.get("bodyDiscount")));
//			Double unitDiscount = Double.valueOf(String.valueOf(data.get("unitDiscount")));
			
			String result = fryeService.putJason("/order/callback", data);
			log.info("BPM Callback result : " + result);
	    	return true;
	    }
	  
	  
}
