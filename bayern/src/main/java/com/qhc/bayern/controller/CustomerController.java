/**
 * 
 */
package com.qhc.bayern.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qhc.bayern.service.LocationService;

import io.swagger.annotations.Api;

/**
 * @author wang@dxc.com
 *
 */
@RestController
@Api(value = "Customer data management in Bayern")
@RequestMapping("customer")
public class CustomerController {

	@Autowired
	LocationService locationService;
	
}
