/**
 * 
 */
package com.qhc.bayern.controller.entity;

import java.util.Date;

/**
 * @author wang@dxc.com
 *
 */
public class Material {
	
	private String code;   //matnr  物料编码
	
	private String description;  //maktx 物料描述
	
	private boolean isConfigurable;//kzkfg 是否可配置物料
	
	private boolean isPurchased;//物料属性
	
	private double standardPrice;//verpr  标准价格
	
	private Date optTime;//laeda 
	
	private String unitCode;//meins  计量单位
	
	private String groupCode;//matkl 物料分组
	
	private String clazzCode;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isConfigurable() {
		return isConfigurable;
	}

	public void setConfigurable(boolean isConfigurable) {
		this.isConfigurable = isConfigurable;
	}

	public boolean isPurchased() {
		return isPurchased;
	}

	public void setPurchased(boolean isPurchased) {
		this.isPurchased = isPurchased;
	}

	public double getStandardPrice() {
		return standardPrice;
	}

	public void setStandardPrice(double standardPrice) {
		this.standardPrice = standardPrice;
	}

	public Date getOptTime() {
		return optTime;
	}

	public void setOptTime(Date optTime) {
		this.optTime = optTime;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getClazzCode() {
		return clazzCode;
	}

	public void setClazzCode(String clazzCode) {
		this.clazzCode = clazzCode;
	}

	
	
}
