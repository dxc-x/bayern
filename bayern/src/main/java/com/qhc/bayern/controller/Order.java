/**
 * 
 */
package com.qhc.bayern.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author wang@dxc.com
 *
 */
@RestController
@Api(value = "Order Manager")
@RequestMapping("/order")
public class Order {
	
	  @ApiOperation(value="push a new order to SAP")
	  @PostMapping(value = "/push")
	  @ResponseStatus(HttpStatus.OK)
	  public String getUserList(@RequestParam String number) throws Exception
	  {
		 return number;
	}

}
