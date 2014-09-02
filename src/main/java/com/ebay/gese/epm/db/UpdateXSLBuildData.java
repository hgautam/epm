package com.ebay.gese.epm.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class UpdateXSLBuildData {
	final static Logger logger = Logger.getLogger(UpdateXSLBuildData.class);
	public static void updateBuildStatus(int runid, String status, long requestid, long projectid, long builidid, int attempt) {
		Connection conn = new ConnectionProvider().connection();
		String sql = ("update xslbuildtaskdetail set status = '"+ status +"', projectid ="+ projectid + ", requestid ="+ requestid +", buildid =" + builidid +
				", attempt ="+ attempt +" where RUNID =" + runid);
		Statement stmt = null;

		try {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error(e);
		} finally {
			ConnectionProvider.close(stmt);
			ConnectionProvider.close(conn);
		}
	}
	
	public static void updateXslBuildId(long buildid, long projectid, long requestid) {
		Connection conn = new ConnectionProvider().connection();
		String sql = ("update xslbuildtaskdetail set xslbuild = "+ buildid +" where projectid =" + projectid +" and requestid = "+ requestid);
		Statement stmt = null;

		try {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error(e);
		} finally {
			ConnectionProvider.close(stmt);
			ConnectionProvider.close(conn);
		}
	}
	public static void updateXslBuildStatus(long projectid, long requestid, String status, long buildid) {
		Connection conn = new ConnectionProvider().connection();
		String sql = ("update xslbuildtaskdetail set status = '"+ status +"', buildid =" + buildid +" where requestid =" + requestid +" and projectid = "+ projectid);
		Statement stmt = null;

		try {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);

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
		//updateBuildStatus(457, "v3syicore", "ready to start");
		//updateBuildStatus(457, "Submitted", 6788, 7899);
	}

}
