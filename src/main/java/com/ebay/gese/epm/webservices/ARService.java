package com.ebay.gese.epm.webservices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.ebay.gese.epm.db.InsertRolloutData;
import com.ebay.gese.epm.db.PoolDAO;
import com.ebay.gese.epm.db.RolloutTO;

public class ARService {
	final static Logger logger = Logger.getLogger(ARService.class);
	public static void monitorRollout(long tcrollid) {
		String output;
		StringBuilder sb = new StringBuilder();
		URL url;
		HttpURLConnection conn = null;
		try {
			url = new URL("http://autoroller-test.stratus.dev.ebay.com/AutoRoll/AutoRollServlet?command=currenttrstatus&tcrollid="+ tcrollid + "&outputType=json");
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			if (conn.getResponseCode() != java.net.HttpURLConnection.HTTP_OK) {
				//System.out.println("status code is:" + conn.getResponseCode());
				logger.error("status code is:" + conn.getResponseCode());
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));
			//System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				// System.out.println(output);
				  sb.append(output);
				
			}
			/*		
			String [] formattedOutput = sb.toString().split("%%");
			
			for (int i = 0; i < formattedOutput.length; i++) {
				//System.out.println(formattedOutput[i]);
				Pattern pattern = Pattern.compile("status");
				Matcher matcher = pattern.matcher(formattedOutput[i]);
			    while (matcher.find()) {
			        //System.out.print("Start index: " + matcher.start());
			        //System.out.print(" End index: " + matcher.end() + " ");
			        //System.out.println(matcher.group());
			        System.out.println(formattedOutput[i]);
			        String [] status = formattedOutput[i].split("~~~");
			        System.out.println(status[1]);
			        String [] instatus = status[1].split("\\^");
			        System.out.println(instatus[1]);
			        if (instatus[1] != null) {
			        	InsertRolloutData.updateRollout(tcrollid, instatus[1]);
			        }
			    }
			} */
			JSONParser parser = new JSONParser();
			// Object obj1 = parser.parse(output);
			Object obj = null;
			try {
				obj = parser.parse(sb.toString());
			} catch (org.json.simple.parser.ParseException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				logger.error(e);
			}
			
			JSONObject jsonObject = (JSONObject) obj;
			if (jsonObject != null) {
	                        String status = (String)jsonObject.get("Status");
	                        if (status != null) {
	                        	logger.debug("Monitoring status is "+ status);
	                        	InsertRolloutData.updateRollout(tcrollid, status);
	                        }  else {
	                        	//System.out.println("Not getting correct monitoring status from AR!");
	                        	logger.debug("Not getting correct monitoring status from AR!");
	                        	//System.out.println("Here is the status: "+ status);
	                        	logger.debug("Here is the status: "+ status);
	                        }
			}
			
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
			logger.error(e1);
		} catch (ProtocolException e2) {
			//e2.printStackTrace();
			logger.error(e2);
		} catch (IOException e3) {
			//e3.printStackTrace();
			logger.error(e3);
		}
		conn.disconnect();
	}
	
	public static void submitRollout(int runid, long javabuild, long xslbuild,
			String poolname, int attempt, int train) {

		String output;
		StringBuilder sb = new StringBuilder();
		URL url = null;
		HttpURLConnection conn = null;
		// get the poolname first
		String pName = PoolDAO.getPoolname(poolname);

		try {
			if (!PoolDAO.xslEnabled(pName)) {
				//System.out.println(poolname + " does not need xsl build.");
				logger.debug(poolname + " does not need xsl build.");
				url = new URL(
						"http://autoroller-test.stratus.dev.ebay.com/AutoRoll/AutoRollServlet?command=getid&java="
								+ javabuild
								+ "&pool="
								+ poolname
								+ "&skipstaging=false&skipqa=true&isPausedf=false&forcerollout=true&rollimm=true&override=true&train="
								+ train + "&outputType=json");
			} else {
				url = new URL(
						"http://autoroller-test.stratus.dev.ebay.com/AutoRoll/AutoRollServlet?command=getid&java="
								+ javabuild
								+ "&xsl="
								+ xslbuild
								+ "&pool="
								+ poolname
								+ "&skipstaging=false&skipqa=true&isPausedf=false&forcerollout=true&rollimm=true&override=true&train="
								+ train + "&outputType=json");
			}
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			if (conn.getResponseCode() != java.net.HttpURLConnection.HTTP_OK) {
				System.out.println("status code is:" + conn.getResponseCode());
				logger.error("status code is:" + conn.getResponseCode());
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));
			//System.out.println("Output from Server ....");
			while ((output = br.readLine()) != null) {
				//System.out.println(output);
				sb.append(output);
			}
			/*
			 * String [] httpOutput = sb.toString().split("\\^\\^");
			 * 
			 * String [] rolloutid = httpOutput[1].split(":");
			 * 
			 * String rollout = rolloutid[1].replace(" ", "");
			 * 
			 * //System.out.println("rollout id is "+ rollout);
			 * 
			 * int tcrollid = Integer.parseInt(rollout);
			 */
			JSONParser parser = new JSONParser();
			// Object obj1 = parser.parse(output);
			Object obj = null;
			try {
				obj = parser.parse(sb.toString());
			} catch (org.json.simple.parser.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			JSONObject jsonObject = (JSONObject) obj;
			if (jsonObject != null) {
				String status = (String) jsonObject.get("Status");
				if (status.equals("OK")) {
					long tcrollid = (Long) jsonObject.get("ARID");

					//System.out.println("tcrollid is " + tcrollid);
					logger.debug("tcrollid is " + tcrollid);

					RolloutTO rto = new RolloutTO();
					rto.setRunid(runid);
					rto.setStatus("Submitted");
					rto.setTcrollid(tcrollid);
					rto.setAttempt(attempt);
					rto.setPoolname(poolname);

					InsertRolloutData.updateRollout(rto);
				} else {
					status = (String) jsonObject.get("status");
					//System.out.println("Not getting OK status from AR API...");
					logger.debug("Not getting OK status from AR API...");
					//System.out.println("Here is the status " + status);
					logger.debug("Here is the status " + status);
				}
			}

		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
			logger.error(e1);
		} catch (ProtocolException e2) {
			//e2.printStackTrace();
			logger.error(e2);
		} catch (IOException e3) {
			//e3.printStackTrace();
			logger.error(e3);
		}
		conn.disconnect();
	}
	
	public static void main (String [] args) {
		monitorRollout(99580);
		//submitRollout(123, 223423, 234323, "v3syicore", 1, 877 );
	}

}
