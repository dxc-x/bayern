package com.qhc.bayern.service.scheduler;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.qhc.bayern.service.MaterialService;
import com.qhc.bayern.service.OrderService;

/**
 * @author wang@dxc.com
 *
 */
public class MyCronJob implements Job {
	
	@Autowired
	private OrderService orderService; 
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		System.out.println("MyCronJob");
		orderService.updateStatus();
	}

}
