/**
 * 
 */
package com.qhc.bayern.controller.entity;

import java.util.Date;

/**
 * @author wang@dxc.com
 *
 */
public class Customer extends AbsConObject {
	
	private Date changedDate;
	private String address;
//	private String groupCode;
	private String clazzCode;
//	private String levelCode;
	private String affiliationCode;//Industry
	private String affiliationName;//Industry name
	
	public Date getChangedDate() {
		return changedDate;
	}
	public void setChangedDate(Date changedDate) {
		this.changedDate = changedDate;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	

	public String getClazzCode() {
		return clazzCode;
	}
	public void setClazzCode(String clazzCode) {
		this.clazzCode = clazzCode;
	}

	
	public String getAffiliationCode() {
		return affiliationCode;
	}
	public void setAffiliationCode(String affiliationCode) {
		this.affiliationCode = affiliationCode;
	}
	public String getAffiliationName() {
		return affiliationName;
	}
	public void setAffiliationName(String affiliationName) {
		this.affiliationName = affiliationName;
	}
	
}
