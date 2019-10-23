/**
 * 
 */
package com.qhc.bayern.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qhc.bayern.controller.entity.Customer;
import com.qhc.bayern.controller.entity.SalesGroup;
import com.qhc.bayern.service.CustomerService;
import com.qhc.bayern.service.LocationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author wang@dxc.com
 *
 */
@RestController
@Api(value = "Customer data management in Bayern")
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	@ApiOperation(value = "retrieve customer data from SAP to DB")
	@GetMapping(value = "customer")
	@ResponseStatus(HttpStatus.OK)
	public void getCustomers() throws Exception {
		String update = customerService.getLastUpdate();
		List<Customer> temp = customerService.getCustomersFromSap(update);
		customerService.uploadCustomers(temp);
	}
	
}
