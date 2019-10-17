package com.qhc.bayern.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qhc.bayern.controller.entity.Parameter;
import com.qhc.bayern.controller.entity.SalesGroup;
import com.qhc.bayern.util.HttpUtil;


/**
 * @author wang@dxc.com
 *
 */
@Service
public class LocationService {
	
	@Autowired
	private FryeService<List<SalesGroup>> frye;
	
	@Value("${sap.sapOfficeGroup.addr}")
	String sapOfficeGroupUrlStr;
	
	private final static String SALES_OFFICES = "location/salesOffice";

	/**
	 * save the sales groups with sales offices to DB in Frye
	 */
	public void save(List<SalesGroup> groups) throws Exception {
	
		frye.putJason(SALES_OFFICES, groups);

	}

	/**
	 * 
	 * @return sale office and sale group from sap
	 */
	public List<SalesGroup> getSalesgroupFromSAP() {
		List<SalesGroup> rl = new ArrayList<SalesGroup>();
		try {
			//接口请求参数
			Parameter parameter2 = new Parameter();
			parameter2.setKey("LANGU");
			parameter2.setValue("1");
			List<Parameter> parList = new ArrayList<Parameter>();
			parList.add(parameter2);
			String sapOfficeGroupParam = JSONObject.toJSONString(parList);
			//发送请求获取数据
			String bb = HttpUtil.postbody(sapOfficeGroupUrlStr, sapOfficeGroupParam);
			JSONObject parseObject = JSONObject.parseObject(bb);
			Object message = parseObject.get("message");
//			Object officeData = parseObject.get("sales_office");
			Object groupData = parseObject.get("sales_group");
			JSONArray groupDataArray = JSONArray.parseArray(groupData.toString());
			for (int i = 0; i < groupDataArray.size();i++) { 
				JSONObject obj = (JSONObject)groupDataArray.get(i);
				SalesGroup sg1 = new SalesGroup();
				sg1.setCode(obj.getString("vkgrp"));
				sg1.setName(obj.getString("vkgrptext"));
				sg1.setOfficeCode(obj.getString("vkbur"));
				sg1.setOfficeName(obj.getString("vkburtext"));
				rl.add(sg1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
//		SalesGroup sg1 = new SalesGroup();
//		sg1.setCode("S01");
//		sg1.setName("青岛");
//		sg1.setOfficeCode("Z001");
//		sg1.setOfficeName("东区");
//		rl.add(sg1);
		return rl;
	}

}
