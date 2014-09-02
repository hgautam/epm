package com.ebay.gese.epm.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

public class PoolDAO {
	final static Logger logger = Logger.getLogger(PoolDAO.class);
	public static String getDeliverable(String poolname) {
		Connection conn = new ConnectionProvider().connection();
		String sql = ("SELECT deliverable from pools where name ='" + poolname + "'");
		Statement stmt = null;
		ResultSet rs = null;
		String deliverable = null;
		try {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				deliverable = rs.getString(1);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error(e);
		} finally {
			ConnectionProvider.close(rs, stmt);
			ConnectionProvider.close(conn);
		}
		return deliverable;
	}
	
	public static boolean xslEnabled(String poolname) {
		
		Connection conn = new ConnectionProvider().connection();
		String sql = ("SELECT xslEnabled from pools where name ='" + poolname + "'");
		Statement stmt = null;
		ResultSet rs = null;
		String xslTag = null;
		boolean result = false;
		try {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				xslTag = rs.getString(1);
				if (xslTag.equals("TRUE")) {
					result = true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error(e);
		} finally {
			ConnectionProvider.close(rs, stmt);
			ConnectionProvider.close(conn);
		}
		return result;
	}
	
	public static String getppPoolname(String poolname) {
		
		Connection conn = new ConnectionProvider().connection();
		String sql = ("SELECT preprodpoolname from pools where name ='" + poolname + "'");
		Statement stmt = null;
		ResultSet rs = null;
		String ppName = null;
		try {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ppName = rs.getString(1);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error(e);
		} finally {
			ConnectionProvider.close(rs, stmt);
			ConnectionProvider.close(conn);
		}
		return ppName;
	}
	
	public static String getPoolname(String poolname) {
		
		Connection conn = new ConnectionProvider().connection();
		String sql = ("SELECT name from pools where preprodpoolname ='" + poolname + "'");
		Statement stmt = null;
		ResultSet rs = null;
		String pName = null;
		try {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				pName = rs.getString(1);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error(e);
		} finally {
			ConnectionProvider.close(rs, stmt);
			ConnectionProvider.close(conn);
		}
		return pName;
	}
	
	public static void main(String [] args) {
		System.out.println(xslEnabled("v3resppintl"));
		String deliverable = getDeliverable("v3syicore");
		System.out.println("deliverable is "+ deliverable);
		String ppPoolname = getppPoolname("v3syicore");
		System.out.println("Preprod pool name is "+ ppPoolname);
	}
}
