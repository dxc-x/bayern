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
import com.qhc.bayern.controller.entity.Customer;
import com.qhc.bayern.util.HttpUtil;

/**
 * @author wang@dxc.com
 *
 */
@Service
public class CustomerService {

	private final static String LAST_UPDATED_DATE = "customer/lastUpdateDate";

	private final static String PUT_CUSTOMER = "customer";

	@Value("${sap.customer.addr}")
	String urlStr;
	
	@Value("${sap.customer.param}")
	String param;

	@Autowired
	private FryeService<List<Customer>> fryeService;

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public Date getLastUpdate() throws Exception {
//		fryeService.getLastUpdatedDate(LAST_UPDATED_DATE);
		return new Date();

	}

	public List<Customer> getCustomersFromSap(Date lastUpdate) {
		List<Customer> clist = new ArrayList<Customer>();
		try {
			String bb = HttpUtil.postbody(urlStr, param);
			JSONObject parseObject = JSONObject.parseObject(bb);
			Object message = parseObject.get("message");
			Object data = parseObject.get("data_cm");
			JSONArray parseArray = JSONArray.parseArray(data.toString());
			for (int i = 0; i < parseArray.size();i++) {
				 JSONObject obj = (JSONObject)parseArray.get(i);
				 //如果sap_industry_code_code为空，赋默认值
				 String industryCode = ("".equals(obj.getString("bran1")))?"no":obj.getString("bran1");
				 
				 if("".equals(obj.getString("kukla")) ) {
					 System.out.println("关键数据不能为空");
					 continue;
				 }else {
					 Customer customer = new Customer();
					 customer.setCode(obj.getString("kunnr"));
					 customer.setName(obj.getString("name1"));
					 customer.setAddress(obj.getString("street"));
					 customer.setChangedDate(new Date());
					 customer.setClazzCode(obj.getString("kukla"));
					 customer.setAffiliationCode(obj.getString("brsch"));
					 customer.setAffiliationName(obj.getString("brtxt"));
					 customer.setIndustryCodeCode(industryCode);
					 clist.add(customer);
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clist;
/*		
		Customer c1 = new Customer();
		c1.setCode("1234567812345678");
		c1.setName("customer1");
		c1.setChangedDate(new Date(1008105271098L));
		c1.setAddress("address");
		c1.setClazzCode("01");
		c1.setLevelCode("0001");
		c1.setAffiliationCode("0011");
		c1.setAffiliationName("0011name");

		clist.add(c1);
		return clist;  */
	}

	/**
	 * 
	 * @param groups
	 * @throws Exception
	 */
	public void uploadCustomers(List<Customer> customers) throws Exception {

		fryeService.postJason(PUT_CUSTOMER, customers);
		
	}
	
	

}
