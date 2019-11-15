package com.qhc.bayern.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qhc.bayern.controller.entity.CharacteristicValue;
import com.qhc.bayern.controller.entity.Clazz;
import com.qhc.bayern.controller.entity.Currency;
import com.qhc.bayern.controller.entity.Customer;
import com.qhc.bayern.controller.entity.DefaultCharacteristics;
import com.qhc.bayern.controller.entity.Incoterm;
import com.qhc.bayern.controller.entity.PaymentPlan;
import com.qhc.bayern.controller.entity.Price;
import com.qhc.bayern.controller.entity.SalesGroup;
import com.qhc.bayern.service.CharacteristicService;
import com.qhc.bayern.service.CurrencyService;
import com.qhc.bayern.service.CustomerService;
import com.qhc.bayern.service.LocationService;
import com.qhc.bayern.service.MaterialService;
import com.qhc.bayern.service.OrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "同步sap数据进入销售工具", description = "同步sap数据进入销售工具")
public class SapToSellingToolController {
	
	@Autowired
	CharacteristicService charaService;
	
	@Autowired
	private CurrencyService currencyService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	LocationService locationService;
	
	@Autowired
	private MaterialService materialService;
	
	@Autowired
	private OrderService orderService;
	
/*	1.currency
	2.incoterm
	3.class
	4.customer
	5.offices
	6.paymentPlan
	7.CharacteristicValue
	8.materials
	9.price
	10.priceA
	11.defaultCharacteristic */
	
	@ApiOperation(value = "同步sap数据进入销售工具")
	@GetMapping(value = "SapToSellingTool")
	@ResponseStatus(HttpStatus.OK)
	public void sapToSellingTool() {
		try {
			//currency
			List<Currency> temp = currencyService.getCurrencyFromSap(new Date());
			currencyService.uploadCurrency(temp);
			//incoterm
			List<Incoterm> temp1 = currencyService.getIncotermFromSap();
			currencyService.uploadIncoterm(temp1);
			//class
			List<Clazz> clazz = charaService.getClassesFromSap();
			charaService.uploadClass(clazz);
			//customer
			String update = customerService.getLastUpdate();
			List<Customer> temp2 = customerService.getCustomersFromSap(update);
			customerService.uploadCustomers(temp2);
			//offices
			List<SalesGroup> temp3 = locationService.getSalesgroupFromSAP();
			locationService.save(temp3);
			//paymentPlan
			List<PaymentPlan> lp = orderService.getPaymentFromSAP();
			orderService.savePaymentPlan(lp);
			//CharacteristicValue
			List<CharacteristicValue>  values = charaService.getClassesAndCharacteristicValueFromSap();
			charaService.uploadCharacteristicValue(values);
			//materials
			materialService.saveNewestMaterialsFromSap();
			//price
			String update1 = currencyService.getLastUpdate();
			List<Price> temp4 = currencyService.getPriceFromSap(update1);
			currencyService.uploadPrice(temp4);
			//priceA
			String update2 = currencyService.getLastUpdate();
			List<Price> temp5 = currencyService.getPriceAFromSap(update2);
			currencyService.uploadPriceA(temp5);
			//defaultCharacteristic
			List<DefaultCharacteristics> defaultList = charaService.getConfigurationProfile();
			charaService.uploadCharacteristicDefault(defaultList);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
