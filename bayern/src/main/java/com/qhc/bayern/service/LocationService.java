package com.qhc.bayern.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

import com.qhc.bayern.controller.entity.Order;
import com.qhc.bayern.controller.entity.SalesGroup;

import reactor.core.publisher.Mono;

/**
 * @author wang@dxc.com
 *
 */
@Service
public class LocationService extends AbsFryeService {
	@Autowired
	ApplicationConfiguration configService;
	
	private final static String SALES_OFFICES = "location/salesOffices";

	/**
	 * save the sales groups with sales offices to DB in Frye
	 */
	public void save(List<SalesGroup> groups) throws Exception {
		
	//	this.get(configService.getFryeServer(),"location/test");
		this.putJason(configService.getFryeServer()+SALES_OFFICES, groups,SalesGroup.class);
//		System.out.println("updateStatus:" + resp.toString());
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
		sg2.setCode("S002");
		sg2.setName("济南");
		sg2.setOfficeCode("Z001");
		sg2.setOfficeName("东区");
		SalesGroup sg3 = new SalesGroup();
		sg3.setCode("S003");
		sg3.setName("哈尔滨");
		sg3.setOfficeCode("Z002");
		sg3.setOfficeName("东北区");
		SalesGroup sg4 = new SalesGroup();
		sg4.setCode("S004");
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
