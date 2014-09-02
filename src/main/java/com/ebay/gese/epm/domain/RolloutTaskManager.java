package com.ebay.gese.epm.domain;


import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.ebay.gese.epm.db.BuildDAO;
import com.ebay.gese.epm.db.BuildTO;
import com.ebay.gese.epm.db.EpmDAO;
import com.ebay.gese.epm.db.RolloutDAO;
import com.ebay.gese.epm.db.RolloutTO;
import com.ebay.gese.epm.webservices.ARService;
import com.ebay.gese.epm.webservices.SubmitBuildService;

/*
 * This class runs on a pre-scheduled times and 
 * submits builds 
 */

public class RolloutTaskManager implements Job {
	final static Logger logger = Logger.getLogger(RolloutTaskManager.class);
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		//find out number of builds eligible for submission
		//find current train and run id
		int runid = EpmDAO.getCurrentRunid();
		//int runid = 531;
		//Call build Task domain object and ask to do the work
		ArrayList<RolloutTO>poolsToMonitor = RolloutDAO.getPoolsToRoll(runid);
				
		if (poolsToMonitor.size() < 1) {
			//System.out.println("No rollouts to submit at this time..");
			logger.debug("No rollouts to submit at this time..");
			System.exit(0);
		}
	
		for (RolloutTO rto : poolsToMonitor) {
				//System.out.println("in main poolname " + rto.getPoolname());
				logger.debug("in main poolname " + rto.getPoolname());
				//System.out.println("in main " + rto.getJavabuild());
				logger.debug("in main " + rto.getJavabuild());
				//System.out.println("in main " + rto.getXslbuild());
				logger.debug("in main " + rto.getXslbuild());
				//System.out.println("in main " + rto.getTrain());
				logger.debug("in main " + rto.getTrain());
				//System.out.println("in main " + rto.getAttempt());
				logger.debug("in main " + rto.getAttempt());
				//System.out.println("in main preprod poolname " + rto.getPpPoolname());
				int attempt = rto.getAttempt() + 1;
				ARService.submitRollout(runid, rto.getJavabuild(), rto.getXslbuild(), rto.getPoolname(), attempt, rto.getTrain());
		 }
		
		//System.out.println("******End of RUN****");
		logger.debug("******End of RUN****");
		System.exit(0);
	}
}
