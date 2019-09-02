/**
 * 
 */
package com.qhc.bayern.controller.entity;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

/**
 * @author wang@dxc.com
 *
 */
public class Order {
	@NotBlank(message = "code not able to be empty")
	private String code;
	
	
	private int status;


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}

	
	

}
