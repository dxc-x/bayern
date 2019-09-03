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
	
	public final static String SIGN_PROTOCAL = "://";
	public final static String SIGN_SEGAMENT = "/";
	public final static String SIGN_PORT = ":";

	public String getFryeServer(){
		

		return apConfig.getProtocal()+SIGN_PROTOCAL+apConfig.getFryeServer()
		+ SIGN_PORT + String.valueOf(apConfig.getFryePort())+SIGN_SEGAMENT
		+ apConfig.getApplication()+SIGN_SEGAMENT;
	}

}
