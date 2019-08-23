/**
 * 
 */
package com.qhc.bayern.service.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author wang@dxc.com
 *
 */
@Service
public class TJob {

	/**
	 * @param args
	 */
	@Scheduled(cron = "000/600 * * * * ?")
    public void printTime() {
		System.out.println("current time :"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

	}

}
