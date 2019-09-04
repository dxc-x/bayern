package com.qhc.bayern.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qhc.bayern.controller.entity.SalesGroup;


/**
 * @author wang@dxc.com
 *
 */
@Service
public class LocationService {
	@Autowired
	ApplicationConfiguration configService;
	
	@Autowired
	FryeService fryeService;
	
	private final static String SALES_OFFICES = "location/salesOffices";

	/**
	 * save the sales groups with sales offices to DB in Frye
	 */
	public void save(List<SalesGroup> groups) throws Exception {
	
		fryeService.putJason(configService.getFryeServer()+SALES_OFFICES, groups,SalesGroup.class);

	}

	/**
	 * 
	 * @return sale office and sale group from sap
	 */
	public List<SalesGroup> getSalesgroup() {
		List<SalesGroup> rl = new ArrayList();
		SalesGroup sg1 = new SalesGroup();
		sg1.setCode("S001");
		sg1.setName("青岛");
		sg1.setOfficeCode("Z001");
		sg1.setOfficeName("东区");
		SalesGroup sg2 = new SalesGroup();
		sg2.setCode("S02");
		sg2.setName("济南");
		sg2.setOfficeCode("Z001");
		sg2.setOfficeName("东区");
		SalesGroup sg3 = new SalesGroup();
		sg3.setCode("S03");
		sg3.setName("哈尔滨");
		sg3.setOfficeCode("Z002");
		sg3.setOfficeName("东北区");
		SalesGroup sg4 = new SalesGroup();
		sg4.setCode("S04");
		sg4.setName("长春");
		sg4.setOfficeCode("Z002");
		sg4.setOfficeName("东北区");

		rl.add(sg1);
		rl.add(sg2);
		rl.add(sg3);
		rl.add(sg4);
		return rl;
	}

}
