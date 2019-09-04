/**
 * 
 */
package com.qhc.bayern.service;

import java.util.ArrayList;
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
	

	private final static String LAST_UPDATED_DATE = "customer/lastUpdateDate/";
	private final static String CODE_CUSTOMER = "59870645008146f9938f7e8818031778";
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public Date getLastUpdate() throws Exception {
		fryeService.getLastUpdatedDate(LAST_UPDATED_DATE, CODE_CUSTOMER);
		return new Date();

	}
	public List<Customer> getCustomersFromSap(Date lastUpdate){
		List<Customer> clist = new ArrayList<Customer>();
		Customer c1 = new Customer();
		c1.setCode("1234567812345678");
		c1.setCode("customer1");
		Customer c2 = new Customer();
		c2.setCode("1234567802345678");
		c2.setCode("customer2");
		
		clist.add(c1);
		clist.add(c2);
		return clist;
	}
	/**
	 * 
	 * @param groups
	 * @throws Exception
	 */
	public void save(List<Customer> customers) throws Exception {
		
		fryeService.putJason(LAST_UPDATED_DATE, customers);

	}
	

}
