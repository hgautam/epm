package com.ebay.gese.epm.domain;

/**
 * This job runs at a pre-determined time
 * to check for Release time stamp from the Release portal
 * This class should represent the system - EPM
 * checking timestamp and other stuff needs to be delegated to
 * appropriate classes
 */
import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.ebay.gese.epm.db.EpmDAO;
import com.ebay.gese.epm.db.EpmTO;
import com.ebay.gese.epm.db.InsertEPMData;
import com.ebay.gese.epm.db.ROPDAO;
import com.ebay.gese.epm.util.DateUtil;
import com.ebay.gese.epm.webservices.DRService;
import com.ebay.gese.epm.webservices.RolloutPortalService;
 
public class EPM implements Job {
	final static Logger logger = Logger.getLogger(EPM.class);
	
	public void execute(JobExecutionContext context)
	throws JobExecutionException {
		//Get current runid and locale
		String currentLocale = EpmDAO.getCurrentLocale();
		//System.out.println("Last run was for "+ currentLocale);
		logger.debug("Last run was for "+ currentLocale);
		
		
		if (currentLocale.equals("intl")) {
			//set current locale as core
			currentLocale = "core";
			//insert core as new locale
			EpmTO epm = new EpmTO("core", DateUtil.getCurrentTime(), "preprod");
			InsertEPMData.insertRow(epm);
		} else {
			currentLocale = "intl";
			//insert intl as new locale
			EpmTO epm = new EpmTO("intl", DateUtil.getCurrentTime(), "preprod");
			InsertEPMData.insertRow(epm);
		}
		
		//Get current train
		int trainid = DRService.getCurrentTrain();
		//System.out.println("Current train is: "+ trainid);
		logger.debug("Current train is: "+ trainid);
		
		//Find if we already have ROP for current train
		
		if (!ROPDAO.getCurrentTrainfromROP(trainid)) {
			//System.out.println("This train does not exist in DB.");
			logger.debug("This train does not exist in DB.");
			RolloutPortalService.addROPPools(trainid);
		}
		
		//getList of ROP pools
		ArrayList <String>poolList = ROPDAO.getROPlist(trainid);
		
		//create a modified list with pools specific to current locale
		ArrayList<String> modifiedList = new ArrayList<String>();
		for (String pool : poolList) {
			if (pool.endsWith(currentLocale)) {
				//System.out.println(pool);
				modifiedList.add(pool);
			} else {
				//System.out.println(pool);
				// System.out.println("Nothing matching found in the list");
			}
		}
		//persist pool build details from Release Portal for an approved build pool
		for (String pool : modifiedList) {
			RolloutPortalService.persistJavaBuildDetails(pool, trainid);
		}
		
		//persist xsl build
		//System.out.println("Checking XSL build.........");
		logger.debug("Checking XSL build.........");
		RolloutPortalService.persistXslBuildDetails(trainid);
		
		//System.out.println("***********Finished current run******************");
		logger.debug("***********Finished current run******************");
		System.exit(0);//this needs to be commented out at some point :-)
		} 
 }