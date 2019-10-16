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
import com.qhc.bayern.controller.entity.Parameter;
import com.qhc.bayern.controller.entity.Price;
import com.qhc.bayern.util.HttpUtil;
import com.qhc.bayern.util.StrToDouble;

/**
 * @author wang@dxc.com
 *
 */
@Service
public class CurrencyService {

	private final static String PUT_CURRENCY = "currency";
	private final static String PUT_INCOTERM = "currency/incoterms";
	private final static String PUT_PRICE = "currency/price";
	
	@Value("${sap.currency.addr}")
	String currencyUrlStr;
	
	@Value("${sap.incoterm.addr}")
	String incotermUrlStr;

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
			//接口请求参数
			Parameter parameter1 = new Parameter();
			parameter1.setKey("KURST");
			parameter1.setValue("M");
			Parameter parameter2 = new Parameter();
			parameter2.setKey("LANGU");
			parameter2.setValue("1");
			List<Parameter> parList = new ArrayList<Parameter>();
			parList.add(parameter1);
			parList.add(parameter2);
			String currencyParam = JSONObject.toJSONString(parList);
			//发送请求获取数据
			String bb = HttpUtil.postbody(currencyUrlStr, currencyParam);
			JSONObject parseObject = JSONObject.parseObject(bb);
			Object message = parseObject.get("message");
			Object data = parseObject.get("data");
			JSONArray parseArray = JSONArray.parseArray(data.toString());
			for (int i = 0; i < parseArray.size();i++) { 
//				  System.out.println(parseArray.get(i)); 
				  JSONObject obj = (JSONObject)parseArray.get(i); 
				  //
				  if(!obj.getString("tcurr").equals("CNY")|| obj.getString("ktext_f")=="") {
					  System.out.println("数据有误");
					  continue;
				  }else {
					  Currency currency = new Currency();
					  currency.setCode(obj.getString("fcurr"));
					  currency.setName(obj.getString("ktext_f"));
//					  currency.setName("111");
					  currency.setRate(StrToDouble.test(obj.getString("ukurs")));
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
		try {
			//接口请求参数
			Parameter parameter1 = new Parameter();
			parameter1.setKey("LANGU");
			parameter1.setValue("1");
			List<Parameter> parList = new ArrayList<Parameter>();
			parList.add(parameter1);
			String incotermParam = JSONObject.toJSONString(parList);
			//发送请求获取数据
			String bb = HttpUtil.postbody(incotermUrlStr, incotermParam);
			JSONObject parseObject = JSONObject.parseObject(bb);
			Object message = parseObject.get("message");
			Object data = parseObject.get("data");
			JSONArray parseArray = JSONArray.parseArray(data.toString());
			for (int i = 0; i < parseArray.size();i++) { 
				JSONObject obj = (JSONObject)parseArray.get(i); 
				Incoterm incoterm = new Incoterm();
				incoterm.setCode(obj.getString("inco1"));
				incoterm.setName(obj.getString("bezei"));
				//全部设置20-出口
				incoterm.setSapSalesTypeCode("20");
				ilist.add(incoterm);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
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
