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
	private String groupCode;
	private String clazzCode;
	private String levelCode;
	private String affiliationCode;
	private String affiliationName;
	
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
	public String getLevelCode() {
		return levelCode;
	}
	public void setLevelCode(String levelCode) {
		this.levelCode = levelCode;
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
