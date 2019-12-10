package com.qhc.bayern.controller.entity;

import java.io.Serializable;

public class Bom implements Serializable{
	private static final long serialVersionUID = 5568458921204355045L;
	private static final int FACTOR = 31;
	
	private String code;//matnr_stpo
	private String parentCode;//matnr
	private int isConfigurable;// kzkfg
	private double price;//stprs
	private double quantity;//menge
	private int isMarked;//mark
	//
	private int isMarkedByDefault;
	private int isMarkedByConfig;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public int getIsConfigurable() {
		return isConfigurable;
	}
	public void setIsConfigurable(int isConfigurable) {
		this.isConfigurable = isConfigurable;
	}
	public int getIsMarked() {
		return isMarked;
	}
	public void setIsMarked(int isMarked) {
		this.isMarked = isMarked;
	}
	public int getIsMarkedByDefault() {
		return isMarkedByDefault;
	}
	public void setIsMarkedByDefault(int isMarkedByDefault) {
		this.isMarkedByDefault = isMarkedByDefault;
	}
	public int getIsMarkedByConfig() {
		return isMarkedByConfig;
	}
	public void setIsMarkedByConfig(int isMarkedByConfig) {
		this.isMarkedByConfig = isMarkedByConfig;
	}
	
	@Override
	public int hashCode() {
		
		return this.getCode().hashCode() * FACTOR+this.getParentCode().hashCode();
	}
	
	@Override
	public boolean equals(Object anObject) {
		if(anObject.getClass().equals(this.getClass()) ) {
			Bom obj = (Bom)anObject;
			if(obj.getCode().equals(this.getCode())&&obj.getParentCode().equals(this.getParentCode())) {
				return true;
			}
		}
		return false;
	}

}
