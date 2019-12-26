package com.qhc.bayern.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderServiceTest {
	Logger logger = LoggerFactory.getLogger("com.qhc.bayern.sap");
	
	@Autowired
	OrderService orderService;

	@Test
	void testGetPaymentFromSAP() {
		fail("Not yet implemented");
	}

}
