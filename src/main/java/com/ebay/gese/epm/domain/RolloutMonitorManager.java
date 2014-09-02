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
import com.ebay.gese.epm.webservices.MonitorBuildService;

public class RolloutMonitorManager implements Job{
	final static Logger logger = Logger.getLogger(RolloutMonitorManager.class);
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		//get current run id
		int runid = EpmDAO.getCurrentRunid();
    	//get list of monitorable pools based on the current id 
		//RolloutDAO should give the list
		ArrayList<RolloutTO>poolsToMonitor = RolloutDAO.getPoolsToMonitor(runid);
		
		if (poolsToMonitor.size() >= 1) {
			for(RolloutTO rto:poolsToMonitor) {
				//System.out.println("TCrollout id is " +rto.getTcrollid());
				logger.debug("TCrollout id is " +rto.getTcrollid());
			    ARService.monitorRollout(rto.getTcrollid());
			}
		} else {
			//System.out.println("Noting to monitor...");
			logger.debug("Noting to monitor...");
		}
		//System.out.println("******End of RUN****");
		logger.debug("******End of RUN****");
		//System.exit(0);
	}
}

