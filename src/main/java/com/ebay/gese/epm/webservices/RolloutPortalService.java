package com.ebay.gese.epm.webservices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.ebay.gese.epm.db.BuildDAO;
import com.ebay.gese.epm.db.BuildTO;
import com.ebay.gese.epm.db.EpmDAO;
import com.ebay.gese.epm.db.InsertBuildData;
import com.ebay.gese.epm.db.InsertROPData;
import com.ebay.gese.epm.db.RopTO;
import com.ebay.gese.epm.util.DateUtil;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

@SuppressWarnings("restriction")
public class RolloutPortalService {
	final static Logger logger = Logger.getLogger(RolloutPortalService.class);
	
	public static void addROPPools (int trainid) {
		StringBuilder sb = new StringBuilder();
		ArrayList<String> poolList = new ArrayList<String>();
		try {

			URL url = new URL(
					"http://release.corp.ebay.com/release/services/rs/pools/query?releaseNumber="+ trainid);
			String authStr = ("em:1cf445b0f9abb43aece17ba15345a016");
            String encoding = Base64.encode (authStr.getBytes());
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty  ("Authorization", "Basic " + encoding);
			conn.setRequestProperty("Content-Type", "application/json");

			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output;
			//System.out.println("Output from Server ....");
			logger.debug("Output from Server ....");
			while ((output = br.readLine()) != null) {
				//sb.append(output);
				logger.debug(output);
				//System.out.println(output);
				logger.debug(output);
			}

			conn.disconnect();

		} catch (MalformedURLException e) {
			//e.printStackTrace();
			logger.error(e);
		} catch (IOException e) {
			//e.printStackTrace();
			logger.error(e);
		}
		JSONParser parser = new JSONParser();
		// Object obj1 = parser.parse(output);
		Object obj = null;
		try {
			obj = parser.parse(sb.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error(e);
			System.exit(1);
		}
		JSONObject jsonObject = (JSONObject) obj;
		String status =  (String)jsonObject.get("status");
		//System.out.println(status);
		logger.debug(status);
		
		//check if status not successful and exit
		
		if (!status.equals("SUCCESS")) {
			//System.out.println("Rollout Portal api call failed. Exiting..");
			logger.debug("Rollout Portal api call failed. Exiting..");
			System.exit(1);
		}
		JSONArray pools = (JSONArray) jsonObject.get("pools");	
		for (int i = 0; i < pools.size(); i++) {
			//System.out.println("Pool is " + pools.get(i));
			logger.debug("Pool is " + pools.get(i));
			poolList.add((String) pools.get(i));
		}
		//Call DRService to check get a formatted lised of RE owned pools
		ArrayList<String> reOwnedPools = DRService.getREOwnedPools(poolList);
		RopTO rop = new RopTO(trainid, reOwnedPools);
		InsertROPData.insertRecord(rop);
	}
	/*
	 * This method not only gets details from RP.
	 * It also persists in DB based on whether its a first time rollout for a train
	 * if it is a subsequent rollout, it will compare the persisted timestamp with incoming timestamp 
	 * and take appropriate action.
	 */
	
	public static void persistJavaBuildDetails(String poolName, int trainid) {

		StringBuilder sb = connectToRp(trainid, poolName);
		JSONParser parser = new JSONParser();
		// Object obj1 = parser.parse(output);
		Object obj = null;
		try {
			obj = parser.parse(sb.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		JSONObject jsonObject = (JSONObject) obj;
		String status =  (String)jsonObject.get("status");
		//System.out.println(status);
		logger.debug(status);
		
		//check if status not successful and exit
		
		if (!status.equals("SUCCESS")) {
			//System.out.println("Rollout Portal api call failed. Exiting..");
			logger.debug("Rollout Portal api call failed. Exiting..");
			System.exit(1);
		}
		
		//System.out.println("poolBuild is "+ jsonObject.get("poolBuild"));
		logger.debug("poolBuild is "+ jsonObject.get("poolBuild"));
		
		if (jsonObject.get("poolBuild") != null) {
			JSONObject innerObject = (JSONObject) jsonObject.get("poolBuild");
			String timeStamp =  (String)innerObject.get("formatedTimestamp");
			//System.out.println("Approved timestamp is "+ timeStamp);
			logger.debug("Approved timestamp is "+ timeStamp);
			long v3buildid =  (Long)innerObject.get("qaV3BuildId");
			//System.out.println("Approved java build is "+ v3buildid);
			logger.debug("Approved java build is "+ v3buildid);
			long xslbuildid =  (Long)innerObject.get("qaXslBuildId");
			//System.out.println("Approved xsl build is "+ xslbuildid);
			logger.debug("Approved xsl build is "+ xslbuildid);
			
			//This is the first time we will insert Data into BuildTaskDetail table
			//get currentRunid
			int runid = EpmDAO.getCurrentRunid();
			String normalizedTimestamp = DateUtil.normalizeDate(timeStamp);
			
			//get DB stored timestamp for last entry of this pool for current train
			//need last runid
			//int previousRunid = EpmDAO.getLastRunid();
			Timestamp time = BuildDAO.getTimestamp(poolName, trainid);
			if (time != null) {
				//System.out.println("db time is "+ time);
				//Timestamp time = BuildDAO.getTimestamp(poolName, previousRunid);;
				String formattedDbDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(time);
				//System.out.println("formatted timestamp is "+ formattedDbDate);
				logger.debug("formatted timestamp is "+ formattedDbDate);
				//System.out.println("Rollout api time is "+ normalizedTimestamp);
				logger.debug("Rollout api time is "+ normalizedTimestamp);
				//compare dbtime with normalized time
				if (DateUtil.compareTimeStamps(normalizedTimestamp, formattedDbDate)) {
					//System.out.println("New timestamp is later than db time stamp");
					logger.debug("New timestamp is later than db time stamp");
					BuildTO bsd = new BuildTO(runid, poolName, v3buildid, xslbuildid, normalizedTimestamp, "INIT", trainid, 0);
					InsertBuildData.setInitData(bsd);
				} else {
					//System.out.println("DB time is current. Nothing to do.");
					logger.debug("DB time is current. Nothing to do.");
				}
				
			} else {
				//this means this is a brand new entry. No previous reference available
				//insert make the first entry
				//System.out.println("No previous reference found. Adding in DB");
				logger.debug("No previous reference found. Adding in DB");
				BuildTO bsd = new BuildTO(runid, poolName, v3buildid, xslbuildid, normalizedTimestamp, "INIT", trainid, 0);
				InsertBuildData.setInitData(bsd);
			}
		}
	}
	
	public static void persistXslBuildDetails(int trainid) {		

				// This is the first time we will insert Data into
				// BuildTaskDetail table
				// get currentRunid
				int runid = EpmDAO.getCurrentRunid();
				//get the distinct xsl build id from db
				long xslbuild = BuildDAO.getXslBuild(runid, trainid);
				
				if (xslbuild != 0) {
					//insert into db
					BuildTO bto = new BuildTO(runid, xslbuild, "INIT", trainid, 0);
					InsertBuildData.setXslInitData(bto);
				} else {
					//System.out.println("Noting new to build xsl");
					logger.debug("Noting new to build xsl");
				}
	}

	public static StringBuilder connectToRp(int trainid, String poolName) {
		StringBuilder sb = new StringBuilder();
		try {
			URL url = new URL(
					"http://release.corp.ebay.com/release/services/rs/pools/builds/query?releaseNumber="
							+ trainid + "&poolName=" + poolName);
			String authStr = ("em:1cf445b0f9abb43aece17ba15345a016");
			String encoding = Base64.encode(authStr.getBytes());

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Authorization", "Basic " + encoding);
			conn.setRequestProperty("Content-Type", "application/json");

			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output;
			//System.out.println("checking for pool " + poolName);
			logger.debug("checking for pool " + poolName);
			// System.out.println("Output from Server ....");
			while ((output = br.readLine()) != null) {
				sb.append(output);
				//System.out.println(output);
			}

			conn.disconnect();

		} catch (MalformedURLException e) {
			//e.printStackTrace();
			logger.error(e);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e);
		}
		return sb;
	}
	/*
	public static void main(String [] args) {
		persistPoolDetails("v3syicore", 875);
	}
	*/
}


