package com.qhc.bayern.controller.entity;

public class PaymentPlan {
	
	private String code;
	
	private String name;
	
	private int paymentTerm;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPaymentTerm() {
		return paymentTerm;
	}

	public void setPaymentTerm(int paymentTerm) {
		this.paymentTerm = paymentTerm;
	}
	

}
