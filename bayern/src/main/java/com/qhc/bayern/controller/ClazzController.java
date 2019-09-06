/**
 * 
 */
package com.qhc.bayern.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qhc.bayern.service.ClassService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author wang@dxc.com
 *
 */
@RestController
@Api(value = "Class data manager in Bayern")
public class ClazzController {
	@Autowired
	private ClassService configService;
	
	@ApiOperation(value="retrieve class data from sap then save to DB")
	@GetMapping(value = "class")
	@ResponseStatus(HttpStatus.OK)
	public void getClasses() throws Exception
	{
		
	}
}
