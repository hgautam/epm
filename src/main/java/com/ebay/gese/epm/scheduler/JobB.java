package com.ebay.gese.epm.scheduler;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
 
public class JobB implements Job {
 
	public void execute(JobExecutionContext context)
		throws JobExecutionException {
		System.out.println("Job B is runing");
	}
 
}
