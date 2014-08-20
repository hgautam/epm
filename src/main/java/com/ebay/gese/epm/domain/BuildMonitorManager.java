package com.ebay.gese.epm.domain;

import java.util.ArrayList;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.ebay.gese.epm.db.BuildDAO;
import com.ebay.gese.epm.db.BuildTO;
import com.ebay.gese.epm.db.EpmDAO;
import com.ebay.gese.epm.webservices.MonitorBuildService;

public class BuildMonitorManager implements Job{
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		//get current run id
		int runid = EpmDAO.getCurrentRunid();
    	//get list of monitorable pools based on the current id 
		//BuildDAO should give the list
		ArrayList<BuildTO>poolsToMonitor = BuildDAO.getPoolsToMonitor(runid);
		
		if (poolsToMonitor.size() >= 1) {
			for(BuildTO bt:poolsToMonitor) {
				//System.out.println("Project id in main " + bt.getProjectId());
			    //System.out.println("Request id in main " + bt.getRequestId());
			    MonitorBuildService.getBuildStatus(bt.getProjectId(), bt.getRequestId());
			}
		} else {
			System.out.println("Noting to monitor...");
		}
		
        //start xsl build monitoring
		BuildTO bto = BuildDAO.getXslToMonitor(runid);
		if (bto != null) {
			MonitorBuildService.getXslBuildStatus(bto.getProjectId(), bto.getRequestId());
		} else {
			System.out.println("Noting to monitor...");
		}
		
		System.out.println("******End of RUN****");
		//System.exit(0);
	}

}
