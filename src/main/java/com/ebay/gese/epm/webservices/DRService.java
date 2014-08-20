package com.ebay.gese.epm.webservices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import java.net.HttpURLConnection;
import java.util.ArrayList;

public class DRService {
	
	public static int getCurrentTrain() {
		String output;
		int trainid = -1;
		URL url;
		HttpURLConnection conn = null;
		try {
			url = new URL("http://autoroller.corp.ebay.com/AutoRoll/AutoRollServlet?command=GetRelease");
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			if (conn.getResponseCode() != java.net.HttpURLConnection.HTTP_OK) {
				System.out.println("status code is:" + conn.getResponseCode());
				System.out.println("DR get current release service is down.");
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));
			// System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				// System.out.println(output);
				trainid = Integer.parseInt(output);
				// System.out.println(trainid);
			}
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ProtocolException e2) {
			e2.printStackTrace();
		} catch (IOException e3) {
			e3.printStackTrace();
		}

		conn.disconnect();
		return trainid;
	}
	public static ArrayList<String> getREOwnedPools(ArrayList<String> poolList) {
		ArrayList<String> reOwnedPoolList = new ArrayList<String>();
		for (String poolName : poolList) {
			if (isREOwned(poolName)){
				System.out.println("added to re-owned "+ poolName);
				reOwnedPoolList.add(poolName);
			}
			 
		}
		return reOwnedPoolList;
	}
	
	public static boolean isREOwned(String poolName) {
		String output;
		boolean value = false;
		URL url;
		HttpURLConnection conn = null;
		try {
			url = new URL("http://autoroller-beta.stratus.dev.ebay.com/AutoRoll/AutoRollServlet?command=istcrolled&pools="+ poolName);
			conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");

			if (conn.getResponseCode() != java.net.HttpURLConnection.HTTP_OK) {
				System.out.println("status code is:" + conn.getResponseCode());
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));
			// System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				//System.out.println(output);
				int len = output.length();
				//System.out.println("String length is "+ len);
				if (output.length() > 5) {
					throw new RuntimeException("Invalid output by autoroller tool for RE owned pool API");
				}
				value = Boolean.parseBoolean(output);
				//System.out.println(value);
			}
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ProtocolException e2) {
			e2.printStackTrace();
		} catch (IOException e3) {
			e3.printStackTrace();
		}

		conn.disconnect();
		return value;

	}
	
	public static void main (String [] args) {
		System.out.println("in main");
		boolean id  = isREOwned("v3syicore");
		System.out.println(id);
	}

}
