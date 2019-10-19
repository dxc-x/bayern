package com.qhc.bayern.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.qhc.bayern.controller.entity.SapCreationOrder;
import com.qhc.bayern.controller.entity.SapOrderCharacteristics;
import com.qhc.bayern.controller.entity.SapOrderHeader;
import com.qhc.bayern.controller.entity.SapOrderItem;
import com.qhc.bayern.controller.entity.SapOrderPlan;
import com.qhc.bayern.controller.entity.SapOrderPrice;

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
		header.setAuart("C001");
		header.setVkorg("0841");
		header.setVtweg("1");
		header.setName2("1");
		header.setSpart("1");
		header.setVkbur("1");
		header.setVkgrp("1");
		header.setVbeln("1");
		header.setKvgr1("1");
		header.setKvgr2("1");
		header.setKvgr3("1");
		header.setBstzd("1");
		header.setBstkdE("1");
		header.setVsart("1");
		header.setZterm("1");
		header.setKunnr("1");
		header.setWaerk("1");
		header.setInco1("1");
		header.setInco2("1");
		header.setVbbkz120("1");
		header.setVbbkz121("1");
		header.setVbbkz109("1");
		header.setVbbkz108("1");
		header.setVbbkz122("1");
		
		List<SapOrderItem> items = new ArrayList<>();
		SapOrderItem item = new SapOrderItem();
		item.setPosnr(10);
		item.setMatnr("1");
		item.setZmeng(1);
		item.setEdatu("20191101");
		item.setPstyv("1");
		item.setVkaus("1");
		item.setStreet("Dalian");
		item.setRegion("1");
		item.setCity1("1");
		item.setCity2("1");
		item.setVbbp0006("1");
		item.setVbbpz121("1");
		item.setVbbpz117("1");
		item.setVbbpz120("1");
		item.setVbbp0007("1");
		items.add(item);
		item = new SapOrderItem();
		item.setPosnr(20);
		item.setMatnr("1");
		item.setZmeng(1);
		item.setEdatu("20191108");
		item.setPstyv("1");
		item.setVkaus("1");
		item.setStreet("Qingdao");
		item.setRegion("1");
		item.setCity1("1");
		item.setCity2("1");
		item.setVbbp0006("1");
		item.setVbbpz121("1");
		item.setVbbpz117("1");
		item.setVbbpz120("1");
		item.setVbbp0007("1");
		items.add(item);
		sapCreationOrder.setItZitem(items);
		
		List<SapOrderCharacteristics> characs = new ArrayList<>();
		SapOrderCharacteristics charac = null;
		charac = new SapOrderCharacteristics();
		charac.setPosnr(10);
		charac.setAtnam("AA");
		charac.setAtwrt("BB");
		characs.add(charac);
		charac = new SapOrderCharacteristics();
		charac.setPosnr(20);
		charac.setAtnam("CC");
		charac.setAtwrt("DD");
		characs.add(charac);
		sapCreationOrder.setItZcharc(characs);
		
		List<SapOrderPrice> prices = new ArrayList<SapOrderPrice>();
		SapOrderPrice price = null;
		price = new SapOrderPrice();
		price.setPosnr(10);
		price.setKschl("A1");
		price.setKbetr(new BigDecimal("99.98"));
		prices.add(price);
		price = new SapOrderPrice();
		price.setPosnr(210);
		price.setKschl("A2");
		price.setKbetr(new BigDecimal("99.99"));
		prices.add(price);
		sapCreationOrder.setItZcond(prices);
		
		List<SapOrderPlan> plans = new ArrayList<SapOrderPlan>();
		SapOrderPlan plan = null;
		plan = new SapOrderPlan();plan.setTetxt("1234");
		plan.setFkdat("20191018");
		plan.setFakwr(new BigDecimal("98"));
		plan.setZterm("C001");
		plans.add(plan);
		plan.setTetxt("5678");
		plan.setFkdat("20191018");
		plan.setFakwr(new BigDecimal("99"));
		plan.setZterm("C002");
		plans.add(plan);
		sapCreationOrder.setItZplan(plans);
		
		return sapCreationOrder;
	}

}
