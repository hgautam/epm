package com.ebay.gese.epm.scheduler;

/*
 * Instrumentation class for Job scheduler
 * 
 * 
 */

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.ebay.gese.epm.domain.EPM;

public class JobScheduler {
	public static void main( String[] args ) throws Exception
    {
     	
    	JobDetail job = JobBuilder.newJob(EPM.class)
		.withIdentity("dummyJobName", "group1").build();
 
	
    	Trigger trigger = TriggerBuilder
		.newTrigger()
		.withIdentity("dummyTriggerName", "group1")
		.withSchedule(
			CronScheduleBuilder.cronSchedule("0/45 * * * * ?"))
		.build();
    	//This trigger will run everyday at 11:00 am
    	/*
    	 trigger = newTrigger()
    			    .withIdentity("trigger3", "group1")
    			    .withSchedule(dailyAtHourAndMinute(11, 00))
    			    .forJob(myJobKey)
    			    .build();
       */
    	//schedule it
    	Scheduler scheduler = new StdSchedulerFactory().getScheduler();
    	scheduler.start();
    	scheduler.scheduleJob(job, trigger);
    }

}
