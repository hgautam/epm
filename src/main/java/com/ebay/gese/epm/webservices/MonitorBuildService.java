package com.ebay.gese.epm.webservices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.ebay.gese.epm.db.BuildDAO;
import com.ebay.gese.epm.db.UpdateBuildData;
import com.ebay.gese.epm.db.UpdateXSLBuildData;

public class MonitorBuildService {
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
		if (jsonObject != null) {
			buildid  =  (Long)jsonObject.get("buildId");
			System.out.println("buildid is "+ buildid);
			UpdateBuildData.updateBuildId(buildid, projectid, requestid);
		}
		return buildid;
	}
	
	public static void getBuildStatus(long projectid, long requestid) {
		//check if buildid needs to be added
		long buildid = BuildDAO.getBuildId(projectid, requestid);
		
		if(buildid == 0) {
			getBuildId(projectid, requestid);
		}
				
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
			if (status != null) {
				UpdateBuildData.updateBuildStatus(projectid, requestid, status);
			}
		}
		//return status;
	}
	
	public static long getXslBuildId(long projectid, long requestid) {
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
		if (jsonObject != null) {
			buildid  =  (Long)jsonObject.get("buildId");
			System.out.println("buildid is "+ buildid);
			UpdateXSLBuildData.updateXslBuildId(buildid, projectid, requestid);
		}
		return buildid;
	}
	
	public static void getXslBuildStatus(long projectid, long requestid) {
		//check if buildid needs to be updated
		//check if projectid and requestid == 0 and return
		if(projectid == 0 || requestid == 0) {
			return;
		}
		
		long buildid = BuildDAO.getXslBuildId(projectid, requestid);
		
		if(buildid == 0) {
			buildid = getXslBuildId(projectid, requestid);
		}
		
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
			if (status != null) {
				UpdateXSLBuildData.updateXslBuildStatus(projectid, requestid, status, buildid);
			}
		}
		//return status;
	}
}
