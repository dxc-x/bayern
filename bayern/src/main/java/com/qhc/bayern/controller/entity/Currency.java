/**
 * 
 */
package com.qhc.bayern.controller.entity;

import java.util.Date;

/**
 * @author wang@dxc.com
 *
 */
public class Currency extends AbsConObject {
	
	private double rate;
	
	private Date effective;

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public Date getEffective() {
		return effective;
	}

	public void setEffective(Date effective) {
		this.effective = effective;
	}
	
}
