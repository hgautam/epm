package com.ebay.gese.epm.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

/*
 * Access Object for any Data in EPM table
 */

public class EpmDAO {
	final static Logger logger = Logger.getLogger(EpmDAO.class);
	public static String getCurrentLocale() {
		Connection conn = new ConnectionProvider().connection();
		String sql = ("SELECT locale FROM epm where RUNID = (SELECT MAX(RUNID) FROM epm)");
		Statement stmt = null;
		ResultSet rs = null;
		String locale = null;
		try {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				  locale   = rs.getString("locale");
				//System.out.println("locale is " + locale);
				logger.debug("locale is " + locale);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionProvider.close(rs, stmt);
			ConnectionProvider.close(conn);
		}
		return locale;
	}
	
	public static int getCurrentRunid() {
		Connection conn = new ConnectionProvider().connection();
		String sql = ("SELECT runid FROM epm where RUNID = (SELECT MAX(RUNID) FROM epm)");
		Statement stmt = null;
		ResultSet rs = null;
		int runid = -1;
		try {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				  runid   = rs.getInt("runid");
				//System.out.println("runid is " + runid);
				logger.debug("runid is " + runid);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionProvider.close(rs, stmt);
			ConnectionProvider.close(conn);
		}
		return runid;
	}
	
	public static int getLastRunid() {
		Connection conn = new ConnectionProvider().connection();
		String sql = ("SELECT runid FROM epm where RUNID = (SELECT MAX(RUNID -2) FROM epm)");
		Statement stmt = null;
		ResultSet rs = null;
		int runid = -1;
		try {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				runid   = rs.getInt("runid");
				//System.out.println("Previous runid is " + runid);
				logger.debug("Previous runid is " + runid);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionProvider.close(rs, stmt);
			ConnectionProvider.close(conn);
		}
		return runid;
	}
	
	/*
	public static void main (String [] args) {
		String name = getCurrentLocale();
		System.out.println("in main " +name);
		int id = getCurrentRunid();
		System.out.println(id);
	}
	*/
  
}
