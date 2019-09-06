/**
 * 
 */
package com.qhc.bayern.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qhc.bayern.controller.entity.Currency;
import com.qhc.bayern.controller.entity.Customer;
import com.qhc.bayern.controller.entity.SalesGroup;

/**
 * @author wang@dxc.com
 *
 */
@Service
public class CustomerService {

	private final static String LAST_UPDATED_DATE = "customer/lastUpdateDate";

	private final static String PUT_CUSTOMER = "customer";
	
	private final static String PUT_CURRENCY = "currency";

	@Autowired
	FryeService fryeService;

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public Date getLastUpdate() throws Exception {
		fryeService.getLastUpdatedDate(LAST_UPDATED_DATE);
		return new Date();

	}

	public List<Customer> getCustomersFromSap(Date lastUpdate) {
		List<Customer> clist = new ArrayList<Customer>();
		Customer c1 = new Customer();
		c1.setCode("1234567812345678");
		c1.setName("customer1");
		c1.setChangedDate(new Date(1008105271098L));
		c1.setAddress("address");
		c1.setClazzCode("01");
		c1.setGroupCode("Z001");
		c1.setLevelCode("0001");
		c1.setAffiliationCode("0011");
		c1.setAffiliationName("0011name");

		Customer c2 = new Customer();
		c2.setCode("1234567802345678");
		c2.setName("customer2");
		c2.setChangedDate(new Date(1008105271098L));
		c2.setAddress("address");
		c2.setClazzCode("01");
		c2.setGroupCode("Z001");
		c2.setLevelCode("0001");
		c2.setAffiliationCode("0012");
		c2.setAffiliationName("0012name");

		clist.add(c1);
		clist.add(c2);
		return clist;
	}

	/**
	 * 
	 * @param groups
	 * @throws Exception
	 */
	public void uploadCustomers(List<Customer> customers) throws Exception {

		fryeService.putJason(PUT_CUSTOMER, customers);

	}
	/**
	 * 
	 * @param groups
	 * @throws Exception
	 */
	public void uploadCurrency(List<Currency> currency) throws Exception {

		fryeService.putJason(PUT_CURRENCY, currency);

	}
	/**
	 * 
	 * @return
	 */
	public List<Currency> getCurrencyFromSap() {
		List<Currency> clist = new ArrayList<Currency>();
		Currency c1 = new Currency();
		c1.setCode("001");
		c1.setName("dollar");
		c1.setRate(32.80000);
		
		Currency c2 = new Currency();
		c2.setCode("002");
		c2.setName("cre");
		c2.setRate(0.25123);
		
		clist.add(c1);
		clist.add(c2);
		return clist;
	}

}
