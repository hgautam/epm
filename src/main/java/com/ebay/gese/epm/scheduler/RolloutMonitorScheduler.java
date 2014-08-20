package com.ebay.gese.epm.scheduler;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.ebay.gese.epm.domain.RolloutMonitorManager;

public class RolloutMonitorScheduler {
	public static void main( String[] args ) throws Exception
    {
     	
    	JobDetail job = JobBuilder.newJob(RolloutMonitorManager.class)
		.withIdentity("dummyJobName", "group1").build();
 
	
    	Trigger trigger = TriggerBuilder
		.newTrigger()
		.withIdentity("dummyTriggerName", "group1")
		.withSchedule(
			CronScheduleBuilder.cronSchedule("0/120 * * * * ?"))
		.build();
 
    	//schedule it
    	Scheduler scheduler = new StdSchedulerFactory().getScheduler();
    	scheduler.start();
    	scheduler.scheduleJob(job, trigger);
 
    }

}
