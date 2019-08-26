/**
 * 
 */
package com.qhc.bayern.controller.entity;

import java.math.BigDecimal;

/**
 * @author wang@dxc.com
 *
 */
public class Material {
	public String code;
	public String name;
	public BigDecimal salePrice;
	public BigDecimal cost;
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
	public BigDecimal getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}
	public BigDecimal getCost() {
		return cost;
	}
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	
	

}
