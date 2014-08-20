package com.ebay.gese.epm.domain;

import java.util.ArrayList;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.ebay.gese.epm.db.BuildDAO;
import com.ebay.gese.epm.db.BuildTO;
import com.ebay.gese.epm.db.EpmDAO;
import com.ebay.gese.epm.db.InsertRolloutData;
import com.ebay.gese.epm.db.PoolDAO;
import com.ebay.gese.epm.db.RolloutTO;



public class RolloutManager implements Job{
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		//get current run id
		int runid = EpmDAO.getCurrentRunid();
    	//get java pools that are ready to be rolled
		
		ArrayList<BuildTO>poolsToMonitor = BuildDAO.getPoolsToRoll(runid);
		
		if (poolsToMonitor.size() >= 1) {
			for(BuildTO bt:poolsToMonitor) {
				System.out.println(bt.getPoolName());
			    System.out.println(bt.getBuildId());
			    RolloutTO rto = new RolloutTO();
			    rto.setRunid(runid);
			    //Moved this logic inside BuildDAO.getPoolstoRoll method
			    /*
			    if (PoolDAO.getppPoolname(bt.getPoolName()) == null) {
			    	System.out.println("No prepod pool name found for "+ bt.getPoolName());
			    	continue;
			    } */
			    rto.setPoolname(PoolDAO.getppPoolname(bt.getPoolName()));
			    rto.setJavabuild(bt.getBuildId());
			    rto.setTrain(BuildDAO.getCurrentTrain(runid));
			    InsertRolloutData.insertRollout(rto);
			}
			//now i want to check for xsl after i have established that i have atleast one java build ready to rollout
			long xslbuild = BuildDAO.getXslToRoll(runid);
			if (xslbuild == 0) {
				System.out.println("No xsl build available to start the rollouts yet!.");
			} else {
				InsertRolloutData.updateXslRollout(runid, xslbuild);
			}
		} else {
			System.out.println("Noting to Rollout yet...");
		}
		System.out.println("******End of RUN****");
		System.exit(0);
	}

}