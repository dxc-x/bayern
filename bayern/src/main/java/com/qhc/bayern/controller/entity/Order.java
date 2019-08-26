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
	
	@NotBlank(message = "creator not able to be empty")
	//@Max(value = 445,message = "too long")
	private String creator;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	
	

}
