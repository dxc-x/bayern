/**
 * 
 */
package com.qhc.bayern.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qhc.bayern.controller.entity.Customer;
import com.qhc.bayern.controller.entity.SalesGroup;

/**
 * @author wang@dxc.com
 *
 */
@Service
public class CustomerService {
	
	
	
	@Autowired
	FryeService fryeService;
	
	private final static String SALES_OFFICES = "customer";
	private final static String LAST_UPDATED_DATE = "lastDate";
	private final static String CODE_CUSTOMER = "59870645-0081-46f9-938f-7e8818031778";
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public Date getLastUpdate() throws Exception {
		fryeService.getLastUpdatedDate(LAST_UPDATED_DATE, CODE_CUSTOMER);
		return null;

	}
	/**
	 * 
	 * @param groups
	 * @throws Exception
	 */
	public void save(List<Customer> customers) throws Exception {
		
		fryeService.putJason(SALES_OFFICES, customers);

	}
	

}
