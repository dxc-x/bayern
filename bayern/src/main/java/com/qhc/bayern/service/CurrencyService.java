/**
 * 
 */
package com.qhc.bayern.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qhc.bayern.controller.entity.Currency;
import com.qhc.bayern.controller.entity.Incoterm;
import com.qhc.bayern.controller.entity.Price;
import com.qhc.bayern.util.HttpUtil;

/**
 * @author wang@dxc.com
 *
 */
@Service
public class CurrencyService {

	private final static String PUT_CURRENCY = "currency";
	private final static String PUT_INCOTERM = "currency/incoterm";
	private final static String PUT_PRICE = "currency/price";
	
	@Value("${sap.currency.addr}")
	String urlStr;
	
	@Value("${sap.currency.param}")
	String param;

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
		try {
			String bb = HttpUtil.postbody(urlStr, param);
			JSONObject parseObject = JSONObject.parseObject(bb);
			Object message = parseObject.get("message");
			Object data = parseObject.get("data");
			JSONArray parseArray = JSONArray.parseArray(data.toString());
			for (int i = 0; i < parseArray.size();i++) { 
//				  System.out.println(parseArray.get(i)); 
				  JSONObject obj = (JSONObject)parseArray.get(i); 
				  //
				  if(obj.getString("tcurr").equals("CNY")) {
					  System.out.println("不是对于人民币的汇率");
					  break;
				  }else {
					  Currency currency = new Currency();
					  currency.setCode(obj.getString("fcurr"));
					  currency.setName(obj.getString("ktext_f"));
					  
					  currency.setRate(Double.valueOf(obj.getString("ukurs")));
					  currency.setEffective(date);
					  clist.add(currency);
				  }
				  
			  }
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	
	/**
	 * 
	 * @return
	 */
	public List<Price> getPriceFromSap(Date date) {
		List<Price> ilist = new ArrayList<Price>();
		Price i1 = new Price();
		i1.setTypeCode("0001");
		i1.setMaterialCode("00001");
		i1.setPrice(18927.12);
		ilist.add(i1);
		
		Price i2 = new Price();
		i2.setTypeCode("0001");
		i2.setMaterialCode("00002");
		i2.setPrice(18927.12);
		ilist.add(i2);	
		return ilist;
	}
	
	public void uploadPrice(List<Price> prices) throws Exception {

		fryeService.putJason(PUT_PRICE, prices);

	}
}
