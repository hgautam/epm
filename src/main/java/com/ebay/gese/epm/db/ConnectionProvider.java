package com.ebay.gese.epm.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.Logger;

public class ConnectionProvider {
	final static Logger logger = Logger.getLogger(ConnectionProvider.class);
	/*
	public static void main(String[] args) throws Exception {
		ConnectionProvider provider = new ConnectionProvider(
				"com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/epm",
				"root", "admin");
		
		
		Connection conn = provider.connection();
		System.out.println(conn);
		printDataSourceStats(dataSource);
		close(conn);
	}
*/
	private static BasicDataSource dataSource = null;
	private String driver ="com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/epm";
	private String user = "root";
	private String password = "admin";

	public ConnectionProvider(String driver, String url, String user,
			String password) {
		/*
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} */
		dataSource = setupDataSource(url, driver, user, password);
	}
	
	public ConnectionProvider() {
		/*
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} */
		dataSource = setupDataSource(url, driver, user, password);
	}

	public synchronized Connection connection() {
		try {
			
			return dataSource.getConnection();
		} catch (SQLException e) {
			//e.printStackTrace();
			logger.error(e);
			return null;
		}
	}

	public static void close(Connection con) {

		try {		
			con.close();
		} catch (SQLException e) {
			//e.printStackTrace();
			logger.error(e);
		}

	}

	public static void close(ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			//e.printStackTrace();
			logger.error(e);
		}

	}

	public static void close(ResultSet rs, Statement stmt) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
		}
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (Exception ex) {
			logger.error(ex);

		}

	}

	public static void close(Statement stmt) {

		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (Exception ex) {
			logger.error(ex);
		}

	}

	private BasicDataSource setupDataSource(String connectURI, String driver, String user,
			String password) {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(driver);
		ds.setUsername(user);
		ds.setPassword(password);
		ds.setUrl(connectURI);
		ds.setMaxActive(-1);
		return ds;
	}
	public static void printDataSourceStats(BasicDataSource bds) {
		   //BasicDataSource bds = (BasicDataSource) ds;
		   System.out.println("NumActive: " + bds.getNumActive());
		   System.out.println("NumIdle: " + bds.getNumIdle());
		  }

}
