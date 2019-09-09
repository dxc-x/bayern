/**
 * 
 */
package com.qhc.bayern.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.qhc.bayern.controller.entity.Currency;
import com.qhc.bayern.controller.entity.Customer;
import com.qhc.bayern.controller.entity.Incoterm;
import com.qhc.bayern.service.CurrencyService;
import com.qhc.bayern.service.CustomerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author wang@dxc.com
 *
 */
@RestController
@Api(value = "Currency and incoterm data management in Bayern")
public class CurrencyController {
	@Autowired
	private CurrencyService currencyService;
	
	@ApiOperation(value = "retrieve customer data from SAP then save to DB")
	@GetMapping(value = "currency")
	@ResponseStatus(HttpStatus.OK)
	public void getCurrency() throws Exception {
		List<Currency> temp = currencyService.getCurrencyFromSap(new Date());
		currencyService.uploadCurrency(temp);
	}
	@ApiOperation(value = "retrieve customer data from SAP then save to DB")
	@GetMapping(value = "currency")
	@ResponseStatus(HttpStatus.OK)
	public void getIncoterm() throws Exception {
		List<Incoterm> temp = currencyService.getIncotermFromSap();
		currencyService.uploadIncoterm(temp);
	}
}
