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
import com.qhc.bayern.controller.entity.Incoterm;

/**
 * @author wang@dxc.com
 *
 */
@Service
public class CurrencyService {

	private final static String PUT_CURRENCY = "currency";
	private final static String PUT_INCOTERM = "incoterm";

	@Autowired
	private FryeService<List<?>> fryeService;
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
	public List<Currency> getCurrencyFromSap(Date date) {
		List<Currency> clist = new ArrayList<Currency>();
		Currency c1 = new Currency();
		c1.setCode("001");
		c1.setName("dollar");
		c1.setRate(32.80000);
		c1.setEffective(date);
		
		Currency c2 = new Currency();
		c2.setCode("002");
		c2.setName("cre");
		c2.setRate(0.25123);
		c2.setEffective(date);
		
		clist.add(c1);
		clist.add(c2);
		return clist;
	}
	/**
	 * 
	 * @param groups
	 * @throws Exception
	 */
	public void uploadIncoterm(List<Incoterm> incoterm) throws Exception {

		fryeService.putJason(PUT_INCOTERM, incoterm);

	}
	/**
	 * 
	 * @return
	 */
	public List<Incoterm> getIncotermFromSap() {
		List<Incoterm> ilist = new ArrayList<Incoterm>();
		Incoterm i1 = new Incoterm();
		i1.setCode("CRZ");
		i1.setName("asd");		
		ilist.add(i1);
		
		Incoterm i2 = new Incoterm();
		i2.setCode("REW");
		i2.setName("name");
		ilist.add(i2);
		
		return ilist;
	}
}
