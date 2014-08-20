package com.ebay.gese.epm.domain;

import java.util.ArrayList;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.ebay.gese.epm.db.BuildDAO;
import com.ebay.gese.epm.db.BuildTO;
import com.ebay.gese.epm.db.EpmDAO;
import com.ebay.gese.epm.webservices.SubmitBuildService;

/*
 * This class runs on a pre-scheduled times and 
 * submits builds 
 */

public class BuildTaskManager implements Job {
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		//find out number of builds eligible for submission
		//find current train and run id
		int runid = EpmDAO.getCurrentRunid();
		//Call build Task domain object and ask to do the work
		ArrayList<BuildTO> poolsToBuild = BuildDAO.getPoolsToBuild(runid);
		
		//System.out.println("Arraylist size is "+ poolsToBuild.size());
		if (poolsToBuild.size() < 1) {
			System.out.println("Nothing to build for java");
			System.out.println("checking for xsl now...");
			BuildTO bto = BuildDAO.getXslToBuild(runid);
			if (bto != null) {
				System.out.println("in main "+ bto.getXslBuildId());
				System.out.println("in main "+ bto.getAttempt());
				int attempt = bto.getAttempt() + 1;
				SubmitBuildService.submitXslBuild(bto.getXslBuildId(), "v3xsl", runid, attempt);
				return;
			} else {
				System.out.println("Nothing to build for xsl. Exitting...");
				System.exit(0);
			}
		}
		
	   //if less than 10 recored set DB status to ready to start
		int buildPool = 10;
		if (poolsToBuild.size() <= buildPool) {
			for (BuildTO bt : poolsToBuild) {
				System.out.println("in main " + bt.getPoolName());
				System.out.println("in main " + bt.getJavaBuildId());
				System.out.println("in main " + bt.getAttempt());
				int attempt = bt.getAttempt() + 1;
				System.out.println("Calling submit build service...");
				SubmitBuildService.submitBuild(bt.getJavaBuildId(), bt.getPoolName(), runid, attempt);
			}
		} else {
			//make a new list with only 10 pools
			ArrayList<BuildTO> modifiedList = new ArrayList<BuildTO>(10);
			for (int i = 0; i < buildPool; i++) {
				modifiedList.add(poolsToBuild.get(i));
			}
			for (BuildTO bt : modifiedList) {
				//update the DB with ready to start
				System.out.println("in main " + bt.getPoolName());
				System.out.println("in main " + bt.getJavaBuildId());
				System.out.println("in main " + bt.getStatus());
				System.out.println("in main " + bt.getAttempt());
				int attempt = bt.getAttempt() + 1;
				SubmitBuildService.submitBuild(bt.getJavaBuildId(), bt.getPoolName(), runid, attempt);
			}
		}
		
		//Next step is to submit xsl build
		BuildTO bto = BuildDAO.getXslToBuild(runid);
		if (bto != null) {
			System.out.println("in main "+ bto.getXslBuildId());
			System.out.println("in main "+ bto.getAttempt());
			int attempt = bto.getAttempt() + 1;
			SubmitBuildService.submitXslBuild(bto.getXslBuildId(), "v3xsl", runid, attempt);
		}
		System.out.println("******End of RUN****");
		System.exit(0);
	}
}
