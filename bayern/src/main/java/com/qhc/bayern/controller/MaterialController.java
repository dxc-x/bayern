package com.qhc.bayern.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qhc.bayern.controller.entity.Customer;
import com.qhc.bayern.controller.entity.Material;
import com.qhc.bayern.controller.entity.Order;
import com.qhc.bayern.service.MaterialService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Material data manager in Bayern")
public class MaterialController {
	
	@Autowired
	private MaterialService materialService;
	
	@ApiOperation(value="retrieve newest material data from SAP and upload to DB")
	@GetMapping(value = "material",produces = "application/json;charset=UTF-8")
	@ResponseStatus(HttpStatus.OK)
	public void getMaterials() throws Exception
	{
//		String update = materialService.getLastUpdate();
		List<Material> temp = materialService.getNewestMaterialsFromSap();
		materialService.uploadMaterials(temp);
	}
}
