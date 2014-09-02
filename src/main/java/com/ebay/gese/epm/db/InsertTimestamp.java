package com.ebay.gese.epm.db;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
/*
 * This class updates timestamp in the db
 */
public class InsertTimestamp {
	private String uploadtime;
	private String csTimestamp;
	
	final static Logger logger = Logger.getLogger(InsertTimestamp.class);
	public InsertTimestamp(String timeStamp) {
		csTimestamp = timeStamp;
		Date dNow = new Date();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		uploadtime = formatter.format(dNow);
	}
	public String getUploadtime() {
		return uploadtime;
	}
	public String getCStimestamp() {
		return csTimestamp;
	}
	public void insertTS() {
		Connection conn = new ConnectionProvider().connection();
		Statement stmt = null;
		String query = "insert into caboose_timestamp ("+
				"timestamp, uploadtime) values" +
				"('"+csTimestamp+"','"+uploadtime+"')";
		 try {
			stmt = conn.createStatement();
			stmt.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error(e);
		} finally {
			ConnectionProvider.close(stmt);
			ConnectionProvider.close(conn);
		}
		
	}
	
	public static void main (String [] args) {
		InsertTimestamp it = new InsertTimestamp("2014-03-11 02:25:38");
		System.out.println("cs time is "+ it.getCStimestamp());
		System.out.println("upload time is "+ it.uploadtime);
		it.insertTS();
		
	}

}
