package com.ebay.gese.epm.domain;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.ArrayList;
import com.ebay.gese.epm.db.BuildDAO;
import com.ebay.gese.epm.db.BuildTO;
import com.ebay.gese.epm.db.EpmDAO;

public class JiraTaskManager implements Job {
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("Running jira monitoring...");
		//Get the current run id
		int runid = EpmDAO.getCurrentRunid();
		//get list of java builds that failed 3 times for current run id
		ArrayList<BuildTO> list = BuildDAO.getFailedBuilds(runid);
		
		if (list.size() > 0) {
			//call Jira service to submit the jira task
		}else {
			System.out.println("No jiras filed for java builds");
		}
		
		//get xsl build that failed 3 times
		long xslbuild = BuildDAO.getFailedXslBuild(runid);
		
		if (xslbuild != 0) {
			//call jira service to submit the jira
		} else {
			System.out.println("No jira filed for xsl build");			
		}
		
		System.out.println("End of run.....");
	}
}
