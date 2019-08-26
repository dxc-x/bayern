/**
 * 
 */
package com.qhc.bayern.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qhc.bayern.controller.entity.Form;
import com.qhc.bayern.controller.entity.Order;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author wang@dxc.com
 *
 */
@RestController
@Api(value = "Order Manager in Bayern")
@RequestMapping("/order")
public class OrderController {
	
	  @ApiOperation(value="push a new order to SAP")
	  @PostMapping(value = "/new",produces = "application/json;charset=UTF-8")
	  @ResponseStatus(HttpStatus.OK)
	  public Order newOrder(@RequestBody(required=true) @Valid Order order,@RequestBody(required=true) @Valid Form form) throws Exception
	  {
		 return order;
	  }
	  @ApiOperation(value="append a form to a order in SAP")
	  @PostMapping(value = "/append",produces = "application/json;charset=UTF-8")
	  @ResponseStatus(HttpStatus.OK)
	  public Form appendForm(@RequestParam(value="orderCode",required=true) String code,@RequestBody @Valid Form form) throws Exception
	  {
		 return form;
	  }
	  @ApiOperation(value="update the order in SAP")
	  @PostMapping(value = "/append",produces = "application/json;charset=UTF-8")
	  @ResponseStatus(HttpStatus.OK)
	  public Order update(@RequestBody(required=true) @Valid Order order) throws Exception
	  {
		 return order;
	  }
	  @ApiOperation(value="notify the order status from SAP")
	  @PostMapping(value = "/status",produces = "application/json;charset=UTF-8")
	  @ResponseStatus(HttpStatus.OK)
	  public int notifyStatus(@RequestParam(value="orderCode",required=true) String code,@RequestParam int status) throws Exception
	  {
		 return status;
	  }
}
