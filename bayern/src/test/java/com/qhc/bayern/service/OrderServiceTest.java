package com.qhc.bayern.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.qhc.bayern.controller.entity.sap.SapCreationOrder;
import com.qhc.bayern.controller.entity.sap.SapOrderCharacteristics;
import com.qhc.bayern.controller.entity.sap.SapOrderHeader;
import com.qhc.bayern.controller.entity.sap.SapOrderItem;
import com.qhc.bayern.controller.entity.sap.SapOrderPlan;
import com.qhc.bayern.controller.entity.sap.SapOrderPrice;

@SpringBootTest
class OrderServiceTest {
	@Autowired
	OrderService orderService;

	@Test
	void testGetPaymentFromSAP() {
		fail("Not yet implemented");
	}

	@Test
	void testOrderCreationForSAP() {
		SapCreationOrder sapCreationOrder = getSapCreationOrder();
		
		String result = orderService.orderCreationForSAP(sapCreationOrder);
		
		System.out.println("Result: %s" + result);
	}
	
	SapCreationOrder getSapCreationOrder() {
		SapCreationOrder sapCreationOrder = new SapCreationOrder();
		
		SapOrderHeader header = new SapOrderHeader();
		sapCreationOrder.setIsZhdr(header);
//		header.setAuart("ZH0T");
//		header.setVkorg("0842");
//		header.setVtweg("1");
//		header.setName2("1");
//		header.setSpart("10");
//		header.setVkbur("C001");
//		header.setVkgrp("C05");
//		header.setVbeln("1");
//		header.setKvgr1("1");
//		header.setKvgr2("1");
//		header.setKvgr3("1");
//		header.setBstzd("1");
//		header.setBstkdE("1");
//		header.setVsart("1");
//		header.setZterm("1");
//		header.setKunnr("3001");
//		header.setWaerk("1");
//		header.setInco1("EXW");
//		header.setInco2("EXW");
//		header.setVbbkz120("1");
//		header.setVbbkz121("1");
//		header.setVbbkz109("1");
//		header.setVbbkz108("1");
//		header.setVbbkz122("1");
		
		// Test data 2019/10/23
		header.setAuart("ZH0T");
		header.setVkorg("0842");
		header.setVtweg("10");
		header.setSpart("10");
		header.setVkbur("C001");
		header.setVkgrp("C05");
		header.setBstzd("1");
		header.setKunnr("3001");
		header.setInco1("EXW");
		header.setInco2("EXW");
		
		List<SapOrderItem> items = new ArrayList<>();
		SapOrderItem item = new SapOrderItem();
//		item.setPosnr(10);
//		item.setMatnr("1");
//		item.setZmeng(1);
//		item.setEdatu("20191101");
//		item.setPstyv("1");
//		item.setVkaus("1");
//		item.setStreet("Dalian");
//		item.setRegion("1");
//		item.setCity1("1");
//		item.setCity2("1");
//		item.setVbbp0006("1");
//		item.setVbbpz121("1");
//		item.setVbbpz117("1");
//		item.setVbbpz120("1");
//		item.setVbbp0007("1");
		
		// Test data 2019/10/23
		item.setPosnr(10);
		item.setMatnr("BG1AM200000");
		item.setZmeng(100);
		
		items.add(item);

		sapCreationOrder.setItZitem(items);
		
		List<SapOrderCharacteristics> characs = new ArrayList<>();
		SapOrderCharacteristics charac = null;
		// Test data 2019/10/23
		charac = new SapOrderCharacteristics();
		charac.setPosnr(10);
		charac.setAtnam("F105");
		charac.setAtwrt("1");
		characs.add(charac);
		charac = new SapOrderCharacteristics();
		charac.setPosnr(10);
		charac.setAtnam("F106");
		charac.setAtwrt("1");
		characs.add(charac);
		charac = new SapOrderCharacteristics();
		charac.setPosnr(10);
		charac.setAtnam("F107");
		charac.setAtwrt("4");
		characs.add(charac);
		charac = new SapOrderCharacteristics();
		charac.setPosnr(10);
		charac.setAtnam("F108");
		charac.setAtwrt("1");
		characs.add(charac);
		charac = new SapOrderCharacteristics();
		charac.setPosnr(10);
		charac.setAtnam("F109");
		charac.setAtwrt("4");
		characs.add(charac);
		charac = new SapOrderCharacteristics();
		charac.setPosnr(10);
		charac.setAtnam("F110");
		charac.setAtwrt("4");
		characs.add(charac);
		charac = new SapOrderCharacteristics();
		charac.setPosnr(10);
		charac.setAtnam("F221");
		charac.setAtwrt("1");
		characs.add(charac);
		charac = new SapOrderCharacteristics();
		charac.setPosnr(10);
		charac.setAtnam("F101");
		charac.setAtwrt("1");
		characs.add(charac);
		sapCreationOrder.setItZcharc(characs);
		
		List<SapOrderPrice> prices = new ArrayList<SapOrderPrice>();
		SapOrderPrice price = null;
		price = new SapOrderPrice();
		price.setPosnr(10);
		price.setKschl("ZH05");
		price.setKbetr(new BigDecimal("2"));
		prices.add(price);
		sapCreationOrder.setItZcond(prices);
		
		List<SapOrderPlan> plans = new ArrayList<SapOrderPlan>();
//		SapOrderPlan plan = null;
//		plan = new SapOrderPlan();
//		plan.setTetxt("Q001");
//		plan.setFkdat("20191010");
//		plan.setFakwr(BigDecimal.valueOf(3));
//		plan.setZterm("Q005");
//		plans.add(plan);
//		plan = new SapOrderPlan();
//		plan.setTetxt("Q002");
//		plan.setFkdat("20191110");
//		plan.setFakwr(BigDecimal.valueOf(4));
//		plan.setZterm("Q010");
//		plans.add(plan);
//		plan = new SapOrderPlan();
//		plan.setTetxt("Q003");
//		plan.setFkdat("20191209");
//		plan.setFakwr(BigDecimal.valueOf(5));
//		plan.setZterm("Q015");
//		plans.add(plan);
//		plan = new SapOrderPlan();
//		plan.setTetxt("Q004");
//		plan.setFkdat("20200109");
//		plan.setFakwr(BigDecimal.valueOf(6));
//		plan.setZterm("Q020");
//		plans.add(plan);
		sapCreationOrder.setItZplan(plans);
		
		return sapCreationOrder;
	}

}
