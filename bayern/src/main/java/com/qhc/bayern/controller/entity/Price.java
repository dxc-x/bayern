/**
 * 
 */
package com.qhc.bayern.controller.entity;

/**
 * @author wang@dxc.com
 *
 */
public class Price {
	private String typeCode;
	private String materialCode;
	private double price;
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getMaterialCode() {
		return materialCode;
	}
	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
}
