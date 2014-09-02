package com.ebay.gese.epm.webservices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.apache.log4j.Logger;

import com.ebay.gese.epm.db.PoolDAO;
import com.ebay.gese.epm.db.RolloutTO;

public class RolloutMonitorService {
	final static Logger logger = Logger.getLogger(RolloutMonitorService.class);
	
	public static void setRolloutStatus(RolloutTO rto) {
		
		
	}
	
	public static void submitRollout(long javabuild, long xslbuild,
			String poolname, int train) {
		// first thing is to find equivalent ppPoolname
		String ppPoolname = PoolDAO.getppPoolname(poolname);

		String output;
		StringBuilder sb = new StringBuilder();
		URL url;
		HttpURLConnection conn = null;
		try {
			url = new URL("http://autoroller.corp.ebay.com/AutoRoll/AutoRollServlet?command=getid&java="+javabuild +
					"&xsl=" + xslbuild +"&pool=" + ppPoolname +"&skipstaging=false&train="+ train);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			if (conn.getResponseCode() != java.net.HttpURLConnection.HTTP_OK) {
				//System.out.println("status code is:" + conn.getResponseCode());
				logger.debug("status code is:" + conn.getResponseCode());
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));
			 //System.out.println("Output from Server .... \n");
			 logger.debug("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				//System.out.println(output);
				logger.debug(output);
				//sb.append(output);
				logger.debug(output);
			}
			
			String [] httpOutput = sb.toString().split(":");
			
			System.out.println(httpOutput[1]);

		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ProtocolException e2) {
			e2.printStackTrace();
		} catch (IOException e3) {
			e3.printStackTrace();
		}
		conn.disconnect();
	}
	
	public static void main (String [] args) {
		submitRollout(12323, 345343, "v3syicore", 877);
	}
}

