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
import com.qhc.bayern.controller.entity.CharacteristicValue;
import com.qhc.bayern.controller.entity.Clazz;
import com.qhc.bayern.controller.entity.DefaultBodyParam;
import com.qhc.bayern.controller.entity.DefaultCharacteristics;
import com.qhc.bayern.controller.entity.DefaultHeadParam;
import com.qhc.bayern.controller.entity.Parameter;
import com.qhc.bayern.util.DateUtil;
import com.qhc.bayern.util.HttpUtil;

/**
 * @author wang@dxc.com
 *
 */
@Service
public class CharacteristicService {
	private final static String PUT_CLASS = "material/materialclass";
	private final static String PUT_CHARACTERISTIC_VALUE= "material/characteristic";
	//
	public final static String sign ="I";
	public final static String option ="EQ";
	
	@Value("${sap.clazz.addr}")
	String clazzUrlStr;
	
	@Value("${sap.characteristic.addr}")
	String characteristicUrlStr;
	
	@Value("${sap.defaultCharacteristics.addr}")
	String defaultUrlStr;
	
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
				JSONObject obj = (JSONObject)parseArray.get(i);
				
				CharacteristicValue c1 = new CharacteristicValue();
				c1.setCode(obj.getString("atwrt"));
				c1.setName(obj.getString("atwtb"));
				c1.setCharacteristicCode(obj.getString("atnam"));
				c1.setCharacteristicName(obj.getString("atbez"));
				c1.setClazzCode(obj.getString("class")); 
				clist.add(c1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clist;
	}
	public void uploadCharacteristicValue(List<CharacteristicValue> chavalue) throws Exception {

		fryeService.putJason(PUT_CHARACTERISTIC_VALUE, chavalue);

	}
	
	//默认特征
	public List<DefaultCharacteristics> getConfigurationProfile(String materCode) {
		List<DefaultCharacteristics> list = new ArrayList<DefaultCharacteristics>();
		try {
			//请求参数
			DefaultBodyParam bodyParam = new DefaultBodyParam();
			bodyParam.setSign(sign);
			bodyParam.setOption(option);
			bodyParam.setLow(materCode);
			List paramlist = new ArrayList<>();
			paramlist.add(bodyParam);
			DefaultHeadParam headParam = new DefaultHeadParam();
			headParam.setAedat("");
//			headParam.setAedat(DateUtil.convert2String(new Date(), "yyyyMMdd"));
			headParam.setItMatnr(paramlist);
			String defaultParam = JSONObject.toJSONString(headParam);
			//发送请求获取数据
			String bb = HttpUtil.postbody(defaultUrlStr, defaultParam);
			JSONObject parseObject = JSONObject.parseObject(bb);
			Object message = parseObject.get("message");
			Object data = parseObject.get("data");
			JSONArray dataArray = JSONArray.parseArray(data.toString());
			for (int i = 0; i < dataArray.size();i++) { 
				JSONObject obj = (JSONObject)dataArray.get(i);
				
				DefaultCharacteristics dc = new DefaultCharacteristics();
				dc.setMaterialCode(obj.getString("matnr"));
				dc.setClassCode(obj.getString("class"));
				dc.setCharacteristic(obj.getString("atnam"));
				dc.setCharacterDescription(obj.getString("atbez"));
				dc.setCharacterValue(obj.getString("atwrt"));
				dc.setCharacterValueDes(obj.getString("atwtb"));
				list.add(dc);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
