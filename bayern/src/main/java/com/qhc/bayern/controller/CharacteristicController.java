/**
 * 
 */
package com.qhc.bayern.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qhc.bayern.controller.entity.CharacteristicValue;
import com.qhc.bayern.controller.entity.Clazz;
import com.qhc.bayern.controller.entity.Currency;
import com.qhc.bayern.controller.entity.DefaultCharacteristics;
import com.qhc.bayern.service.CharacteristicService;
import com.qhc.bayern.service.CustomerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author wang@dxc.com
 *
 */
@RestController
@Api(value = "Characteristic and Class data management in Bayern")
public class CharacteristicController {
	@Autowired
	CharacteristicService charaService;
	
	@ApiOperation(value = "retrieve class data from SAP then save to DB")
	@GetMapping(value = "class")
	@ResponseStatus(HttpStatus.OK)
	public void getClasses() throws Exception {
		List<Clazz> clazz = charaService.getClassesFromSap();
		charaService.uploadClass(clazz);
	}
	
	@ApiOperation(value = "retrieve Characteristic and its value data  from SAP then save to DB")
	@GetMapping(value = "CharacteristicValue")
	@ResponseStatus(HttpStatus.OK)
	public void getCharacteristic() throws Exception {
		List<CharacteristicValue>  values = charaService.getClassesAndCharacteristicValueFromSap();
		charaService.uploadCharacteristicValue(values);
	}
	
	@ApiOperation(value = "retrieve DefaultCharacteristic data from SAP then save to DB")
	@GetMapping(value = "defaultCharacteristic/{materCode}")
	@ResponseStatus(HttpStatus.OK)
	public void getDefaultCharacteristic(
			@PathVariable String materCode
			) throws Exception {
//		String materCode = "BG1QUA00000";
		List<DefaultCharacteristics> defaultList = charaService.getConfigurationProfile(materCode);
		
	}
}
