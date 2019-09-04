/**
 * 
 */
package com.qhc.bayern.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qhc.bayern.controller.entity.Province;
import com.qhc.bayern.controller.entity.SalesGroup;
import com.qhc.bayern.service.LocationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author wang@dxc.com
 *
 */
@RestController
@Api(value = "Location data management in Bayern")
@RequestMapping("location")
public class LocationController {

	@Autowired
	LocationService locationService;
	
	

	@ApiOperation(value = "retrieve sales offices and sales group from SAP to DB")
	@GetMapping(value = "offices")
	@ResponseStatus(HttpStatus.OK)
	public void getSalesOffices() throws Exception {
		List<SalesGroup> temp = locationService.getSalesgroup();
		locationService.save(temp);
	}

}
