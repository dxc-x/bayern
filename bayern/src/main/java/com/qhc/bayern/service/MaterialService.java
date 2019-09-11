package com.qhc.bayern.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qhc.bayern.controller.entity.Customer;
import com.qhc.bayern.controller.entity.Material;


@Service
public class MaterialService {
	
	@Autowired
	private FryeService<List<Material>> fryeService;
	
	private final static String LAST_UPDATED_DATE = "material/lastUpdateDate";
	private final static String PUT_MATERIAL = "material";
	
	public Date getLastUpdate() {
		fryeService.getLastUpdatedDate(LAST_UPDATED_DATE);
		return new Date();
	}
	
	public List<Material> getNewestMaterialsFromSap(Date date) {
		List<Material> mlist = new ArrayList<Material>();
		Material m1 = new Material();
		m1.setCode("123");
		m1.setClazz("001");
		m1.setConfigurable(false);
		m1.setDescription("12312312asdf");
		m1.setMkPrice(123.12);
		m1.setMvPrice(12.12);
		m1.setTrPrice(321.12);
		m1.setType("123");
		m1.setUnit("BOM");
		mlist.add(m1);
		
		Material m2 = new Material();
		m2.setCode("124");
		m2.setClazz("001");
		m2.setConfigurable(false);
		m2.setDescription("12312312asdf");
		m2.setMkPrice(123.12);
		m2.setMvPrice(12.12);
		m2.setTrPrice(321.12);
		m2.setType("123");
		m2.setUnit("BOM");
		mlist.add(m2);
		return mlist;
	}
	
	public void uploadMaterials(List<Material> materials) {
		fryeService.putJason(PUT_MATERIAL, materials);
	}

}
