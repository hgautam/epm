package com.ebay.gese.epm.webservices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.ebay.gese.epm.db.PoolDAO;
import com.ebay.gese.epm.db.UpdateBuildData;
import com.ebay.gese.epm.db.UpdateXSLBuildData;

//import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class SubmitBuildService {
	final static Logger logger = Logger.getLogger(SubmitBuildService.class);
	public static void submitBuild(long buildid, String poolname, int runid, int attempt) {
		StringBuilder sb = new StringBuilder();
		
		String deliverable = PoolDAO.getDeliverable(poolname);
		
		if (deliverable == null) {
			//System.out.println("Deliverable not found for "+ poolname);
			logger.debug("Deliverable not found for "+ poolname);
			UpdateBuildData.updateBuildStatus(runid, poolname, "Rejected");
			return;
		}
		
		//System.out.println("Calling submibBuild inside submitBuildService");
		logger.debug("Calling submibBuild inside submitBuildService");
		
		try {
			URL url = new URL(
					"http://builds.corp.ebay.com/autobuild/servlet/AutoBuildControllerServlet?command=ProdBuildFromQABuildCommand&qaBuildId=" + buildid 
					+ "&requestor=hgautam&sourceBuild=y&buildAsBundled=n&deploymentDeliverables="+ deliverable +"&format=json&preProdEnvType=y");

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/json");

			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output;
			//System.out.println("submitting build for deliverable " + deliverable);
			logger.debug("submitting build for deliverable " + deliverable);
			while ((output = br.readLine()) != null) {
				sb.append(output);
				System.out.println(output);
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
		if (jsonObject != null) {
			long requestid =  (Long)jsonObject.get("requestId");
			long projectid =  (Long)jsonObject.get("projectId");
			//System.out.println(status);
			//System.out.println("requestid is "+ requestid);
			logger.debug("requestid is "+ requestid);
			//System.out.println("project id is "+ projectid);
			logger.debug("project id is "+ projectid);
			UpdateBuildData.updateBuildStatus(runid, poolname, "Submitted", requestid, projectid, 0, attempt);
		} else {
			//System.out.println("Autobuild api returned null");
			logger.debug("Autobuild api returned null");
		}
		//return sb;
	}
	
	public static void submitXslBuild(long buildid, String poolname, int runid, int attempt) {
		StringBuilder sb = new StringBuilder();
		
		String deliverable = PoolDAO.getDeliverable(poolname);
				
		try {
			URL url = new URL(
					"http://builds.corp.ebay.com/autobuild/servlet/AutoBuildControllerServlet?command=ProdBuildFromQABuildCommand&qaBuildId=" + buildid 
					+ "&requestor=hgautam&sourceBuild=y&format=json");

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/json");

			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output;
			//System.out.println("submitting build for deliverable " + deliverable);
			logger.debug("submitting build for deliverable " + deliverable);
			while ((output = br.readLine()) != null) {
				sb.append(output);
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
		if (jsonObject != null) {
			long requestid =  (Long)jsonObject.get("requestId");
			long projectid =  (Long)jsonObject.get("projectId");
			//System.out.println(status);
			//System.out.println("requestid is "+ requestid);
			logger.debug("requestid is "+ requestid);
			//System.out.println("project id is "+ projectid);
			logger.debug("project id is "+ projectid);
			UpdateXSLBuildData.updateBuildStatus(runid, "Submitted", requestid, projectid, 0, attempt);
		} else {
			//System.out.println("Autobuild api returned null");
			logger.debug("Autobuild api returned null");
		}
		//return sb;
	}
	/*
	public static long getBuildId(long projectid, long requestid) {
		StringBuilder sb = new StringBuilder();
		long buildid = 0;
		try {
			URL url = new URL("http://autobuild/autobuild/servlet/AutoBuildControllerServlet?command=GetBuildIdCommand&projectId="+ projectid +"&requestId=" + requestid +"&format=json");
					
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/json");

			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output;
			System.out.println("getting buildid for requestid " + requestid);
			// System.out.println("Output from Server ....");
			while ((output = br.readLine()) != null) {
				sb.append(output);
				System.out.println(output);
			}

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		
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
		buildid  =  (Long)jsonObject.get("buildId");
		
		System.out.println("buildid is "+ buildid);
	
		return buildid;
	}
	
	public static void getBuildStatus(long projectid, long requestid) {
		StringBuilder sb = new StringBuilder();
		String status = null;
		try {
			URL url = new URL("http://autobuild/autobuild/servlet/AutoBuildControllerServlet?command=GetBuildDeployStatusCommand&projectId="+ projectid +"&requestId=" + requestid +"&format=json");
					
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/json");

			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output;
			System.out.println("getting buildid for requestid " + requestid);
			// System.out.println("Output from Server ....");
			while ((output = br.readLine()) != null) {
				sb.append(output);
				System.out.println(output);
			}

			conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		
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
		if (jsonObject != null) {
			status  =  (String)jsonObject.get("buildStatus");
			System.out.println("build status is "+ status);
			//insert build status
			UpdateBuildData.updateBuildStatus(projectid, requestid, status);
		}
		//return status;
	}
	*/

	public static void main (String [] args) {
		submitXslBuild(16868758, "v3xsl", 123, 0);
		//submitBuild(182385, "apifinding", 457, 1);
		//System.out.println(al.toString());
		
	//	long buildid = getBuildId(2918863, 966299);
	//	System.out.println(buildid);
		//String status = getBuildStatus(2918863, 966299);
	//	getBuildStatus(2918863, 966299);
		//System.out.println(status);
	} 

}
