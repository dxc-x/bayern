/**
 * 
 */
package com.qhc.bayern.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qhc.bayern.config.ApplicationConfig;

/**
 * @author wang@dxc.com
 *
 */
@Service
public class ApplicationConfiguration {
	
	@Autowired
	ApplicationConfig apConfig;

	public String getFryeServer(){

		return apConfig.getFryeServer() + ":" + String.valueOf(apConfig.getFryePort());
	}

}
