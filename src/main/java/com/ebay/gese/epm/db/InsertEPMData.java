package com.ebay.gese.epm.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import org.apache.log4j.Logger;

import com.ebay.gese.epm.util.DateUtil;

public class InsertEPMData {
	final static Logger logger = Logger.getLogger(InsertEPMData.class);
	public static void insertRow(EpmTO epm) {
		Connection conn = new ConnectionProvider().connection();
		Statement stmt = null;
		String environment = epm.getEnvironment();
		Timestamp time = epm.getTime();
		String locale = epm.getLocale();
		//System.out.println(environment + "," + time +"," + locale);
		logger.debug(environment + "," + time +"," + locale);
		try {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
			stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO `EPM`.`EPM` (`environment`, `runtime`, `locale`) VALUES ('"+ environment +"','" + time + "','" + locale +"')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error(e);
		} finally {
			ConnectionProvider.close(stmt);
			ConnectionProvider.close(conn);
		}
	}
	/*
	public static void main (String [] args) {
		EpmTO epm = new EpmTO("core", DateUtil.getCurrentTime(), "preprod");
		insertRow(epm);
	}
	*/
}
