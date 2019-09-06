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
	
	public List<Clazz> getClassesAndCharacteristicFromSap() {
		List<Clazz> clist = new ArrayList<Clazz>();
		Clazz c1 = new Clazz();
		c1.setCode("01");
		c1.setName("class1");
		c1.setCharacteristicCode("0001");
		c1.setCharacteristicName("Characteristic1");
		
		Clazz c2 = new Clazz();
		c2.setCode("01");
		c2.setName("class1");
		c2.setCharacteristicCode("0002");
		c2.setCharacteristicName("characteristic2");
		
		clist.add(c1);
		clist.add(c2);
		return clist;
		
	}
	public void uploadClassAndCharacteristic(List<Clazz> clazz) throws Exception {

		fryeService.putJason(PUT_CLASS, clazz);

	}
	
	public List<CharacteristicValue> getClassesAndCharacteristicValueFromSap() {
		List<CharacteristicValue> clist = new ArrayList<CharacteristicValue>();
		CharacteristicValue c1 = new CharacteristicValue();
		c1.setCode("01");
		c1.setName("class1");
	
		
		CharacteristicValue c2 = new CharacteristicValue();
		c2.setCode("01");
		c2.setName("class1");
	
		
		clist.add(c1);
		clist.add(c2);
		return clist;
		
	}
	public void uploadCharacteristicValue(List<CharacteristicValue> chavalue) throws Exception {

		fryeService.putJason(PUT_CHARACTERISTIC_VALUE, chavalue);

	}
	
}
