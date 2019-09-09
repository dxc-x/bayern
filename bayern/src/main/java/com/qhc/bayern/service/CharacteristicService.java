/**
 * 
 */
package com.qhc.bayern.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qhc.bayern.controller.entity.CharacteristicValue;
import com.qhc.bayern.controller.entity.Clazz;
import com.qhc.bayern.controller.entity.Customer;

/**
 * @author wang@dxc.com
 *
 */
@Service
public class CharacteristicService {
	private final static String PUT_CLASS = "class";
	private final static String PUT_CHARACTERISTIC_VALUE= "CharacteristicValue";
	@Autowired
	private FryeService fryeService;
	
	public List<Clazz> getClassesFromSap() {
		List<Clazz> clist = new ArrayList<Clazz>();
		Clazz c1 = new Clazz();
		c1.setCode("01");
		c1.setName("class1");

		
		Clazz c2 = new Clazz();
		c2.setCode("02");
		c2.setName("class2");

		clist.add(c1);
		clist.add(c2);
		return clist;
		
	}
	
	public void uploadClass(List<Clazz> clazz) throws Exception {

		fryeService.putJason(PUT_CLASS, clazz);

	}
	
	public List<CharacteristicValue> getClassesAndCharacteristicValueFromSap() {
		List<CharacteristicValue> clist = new ArrayList<CharacteristicValue>();
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
