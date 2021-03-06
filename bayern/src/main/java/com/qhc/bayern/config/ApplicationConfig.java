package com.qhc.bayern.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author wang@dxc.com
 *
 */

@Configuration
public class ApplicationConfig {
	@Value("${qhc.frye.server}")
	String fryeServer;
	
	@Value("${qhc.frye.port}")
	int fryePort;

	public String getFryeServer() {
		return fryeServer;
	}

	public int getFryePort() {
		return fryePort;
	}

	public void setFryeServer(String fryeServer) {
		this.fryeServer = fryeServer;
	}

	public void setFryePort(int fryePort) {
		this.fryePort = fryePort;
	}


	
	

}
