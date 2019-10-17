/**
 * 
 */
package com.qhc.bayern.service;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qhc.bayern.controller.entity.CharacteristicValue;
import com.qhc.bayern.controller.entity.Clazz;
import com.qhc.bayern.controller.entity.Parameter;
import com.qhc.bayern.util.HttpUtil;

/**
 * @author wang@dxc.com
 *
 */
@Service
public class CharacteristicService {
	private final static String PUT_CLASS = "material/materialclass";
	private final static String PUT_CHARACTERISTIC_VALUE= "material/characteristic";
	
	@Value("${sap.clazz.addr}")
	String clazzUrlStr;
	
	@Value("${sap.characteristic.addr}")
	String characteristicUrlStr;
	
	@Autowired
	private FryeService<List<?>> fryeService;
	
	public List<Clazz> getClassesFromSap() {
		List<Clazz> clist = new ArrayList<Clazz>();
		try {
			//接口请求参数
			Parameter parameter1 = new Parameter();
			parameter1.setKey("LANGU");
			parameter1.setValue("1");
			List<Parameter> parList = new ArrayList<Parameter>();
			parList.add(parameter1);
			String clazzParam = JSONObject.toJSONString(parList);
			//发送请求获取数据
			String bb = HttpUtil.postbody(clazzUrlStr, clazzParam);
			JSONObject parseObject = JSONObject.parseObject(bb);
			Object message = parseObject.get("message");
			Object data = parseObject.get("data");
			JSONArray parseArray = JSONArray.parseArray(data.toString());
			for (int i = 0; i < parseArray.size();i++) {
				JSONObject obj = (JSONObject)parseArray.get(i); 
				Clazz c1 = new Clazz();
				c1.setCode(obj.getString("class"));
				c1.setName(obj.getString("kschl"));
				clist.add(c1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clist;
	}
	
	public void uploadClass(List<Clazz> clazz) throws Exception {

		fryeService.putJason(PUT_CLASS, clazz);

	}
	
	public List<CharacteristicValue> getClassesAndCharacteristicValueFromSap() {
		List<CharacteristicValue> clist = new ArrayList<CharacteristicValue>();
		try {
			//接口请求参数
			Parameter parameter1 = new Parameter();
			parameter1.setKey("LANGU");
			parameter1.setValue("1");
			List<Parameter> parList = new ArrayList<Parameter>();
			parList.add(parameter1);
			String characteristicParam = JSONObject.toJSONString(parList);
			//发送请求获取数据
			String bb = HttpUtil.postbody(characteristicUrlStr, characteristicParam);
			JSONObject parseObject = JSONObject.parseObject(bb);
			Object message = parseObject.get("message");
			Object data = parseObject.get("data");
			JSONArray parseArray = JSONArray.parseArray(data.toString());
			for (int i = 0; i < parseArray.size();i++) {
				CharacteristicValue c1 = new CharacteristicValue();
				c1.setCode("");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		CharacteristicValue c1 = new CharacteristicValue();
		c1.setCode("01");
		c1.setName("value1");
		c1.setCharacteristicCode("100002000030000400005000060000");
		c1.setCharacteristicName("c1");
		c1.setClazzCode("100000200000300000");
		
		CharacteristicValue c2 = new CharacteristicValue();
		c2.setCode("02");
		c2.setName("value2");
		c2.setCharacteristicCode("100002000030000400005000060000");
		c2.setCharacteristicName("c1");
		c2.setName("c1");
		c2.setClazzCode("100000200000300000");
		
		clist.add(c1);
		clist.add(c2);
		return clist;
		
	}
	public void uploadCharacteristicValue(List<CharacteristicValue> chavalue) throws Exception {

		fryeService.putJason(PUT_CHARACTERISTIC_VALUE, chavalue);

	}
	
}
