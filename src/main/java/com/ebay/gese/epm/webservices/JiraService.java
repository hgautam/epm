package com.ebay.gese.epm.webservices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import org.apache.axis.encoding.Base64;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JiraService {
	@SuppressWarnings("unchecked")
	public static void submitJira(ArrayList list) {
		/*
		 * curl -D- -u _epm:_epm123 -k -X POST -d '{"fields": {"project":{"id":
		 * "10490"},"issuetype": {"id": "3"},"summary":
		 * "No REST for the Wicked."}}' -H "Content-Type: application/json"
		 * http://crp-jira03.corp.ebay.com/rest/api/2/issue
		 */
		StringBuilder sb = new StringBuilder();
		URL url;
		HttpURLConnection conn = null;
		try {

			url = new URL("http://crp-jira03.corp.ebay.com/rest/api/2/issue");
			String authStr = ("_epm:_epm123");
			String encoding = Base64.encode(authStr.getBytes());
			conn = (HttpURLConnection) url.openConnection();

			conn.setDoOutput(true);
			conn.setRequestMethod("POST");

			conn.setRequestProperty("Authorization", "Basic " + encoding);
			conn.setRequestProperty("Content-Type", "application/json");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JSONObject outerObject = new JSONObject();
		JSONObject fields = new JSONObject();
		JSONObject project = new JSONObject();
		project.put("id", "10490");
		fields.put("project", project);
		JSONObject issuetype = new JSONObject();
		issuetype.put("id", "3");
		fields.put("issuetype", issuetype);
		fields.put("description", "No Rest for the wicked");
		fields.put("summary", "Test ticket. Please close");
		outerObject.put("fields", fields);

		String input = outerObject.toJSONString();

		System.out.println("input string is: " + input);

		OutputStream os = null;
		try {
			os = conn.getOutputStream();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			os.write(input.getBytes());
			os.flush();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				sb.append(output);
				System.out.println(output);
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		conn.disconnect();

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
		String key = (String) jsonObject.get("key");
		System.out.println(key);

		if (key != null) {
			// call insert data into buildtask table
		}

	}
	
	public static void main(String [] args) {
		ArrayList al = new ArrayList();
		submitJira(al);
		
	}

}
