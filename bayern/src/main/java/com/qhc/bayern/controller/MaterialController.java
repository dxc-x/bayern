package com.qhc.bayern.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qhc.bayern.controller.entity.Material;
import com.qhc.bayern.controller.entity.Order;
import com.qhc.bayern.service.ConfirugrableService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Material Manager in Bayern")
@RequestMapping("material")
public class MaterialController {
	
	@Autowired
	private ConfirugrableService configService;
	
	@ApiOperation(value="retrieve a new material by configable list from SAP")
	@PostMapping(value = "/retrieve",produces = "application/json;charset=UTF-8")
	@ResponseStatus(HttpStatus.OK)
	public Material lookforMaterial(@RequestBody List<String> config) throws Exception
	{
		return new Material();
	}
}
