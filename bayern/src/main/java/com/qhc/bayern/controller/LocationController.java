/**
 * 
 */
package com.qhc.bayern.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qhc.bayern.controller.entity.Form;
import com.qhc.bayern.controller.entity.Order;
import com.qhc.bayern.controller.entity.Province;
import com.qhc.bayern.service.ApplicationConfiguration;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author wang@dxc.com
 *
 */
@RestController
@Api(value = "Location Management in Bayern")
@RequestMapping("location")
public class LocationController {

	@Autowired
	ApplicationConfiguration configService;

	@ApiOperation(value = "retrieve sales offices and sales group from SAP to DB")
	@GetMapping(value = "offices")
	@ResponseStatus(HttpStatus.OK)
	public Province getSalesOffices() throws Exception {
		return new Province();
	}

}
