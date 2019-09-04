/**
 * 
 */
package com.qhc.bayern.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wang@dxc.com
 *
 */
@Service
public class CustomerService {
	
	@Autowired
	ApplicationConfiguration configService;
	
	@Autowired
	FryeService fryeService;
	
	private final static String SALES_OFFICES = "customer";
	
	
	
	

}
