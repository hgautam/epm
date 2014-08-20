package com.ebay.gese.epm.db;

import java.sql.Connection;
import java.sql.SQLException;
//import java.sql.Statement;
//import java.sql.Timestamp;

import java.sql.PreparedStatement;

public class InsertBuildData {
	
	public static void setInitData(BuildTO bsd) {
		Connection conn = new ConnectionProvider().connection();
		PreparedStatement preparedStatement = null;
		int runid = bsd.getRunid();
		String poolname = bsd.getPoolName();
		long javabuildid = bsd.getJavaBuildId();
		long xslbuildid = bsd.getXslBuildId();
		String timestamp = bsd.getTimeStamp();
		String status = bsd.getStatus();
		int train = bsd.getTrainId();
		int attempt = bsd.getAttempt();
		String jiraid = "None";
		
		System.out.println("runid is "+ runid);

        String insertTableSQL = "INSERT INTO EPM.BUILDTASKDETAIL (runid, poolname, javabuild, xslbuild, timestamp, status, projectid, requestid, buildid, train, attempt, jiraid) " +
        		"VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		try {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
			//stmt = conn.createStatement();
			preparedStatement = conn.prepareStatement(insertTableSQL);
			
			preparedStatement.setInt(1, runid);
			preparedStatement.setString(2, poolname);
			preparedStatement.setLong(3, javabuildid);
			preparedStatement.setLong(4, xslbuildid);
			preparedStatement.setString(5, timestamp);
			preparedStatement.setString(6, status);
			preparedStatement.setLong(7, 0);
			preparedStatement.setLong(8, 0);
			preparedStatement.setLong(9, 0);
			preparedStatement.setInt(10, train);
			preparedStatement.setInt(11, attempt);
			preparedStatement.setString(12, jiraid);
			
			preparedStatement .executeUpdate();
			
			//preparedStatement = stmt.prepareStatement(insertTableSQL);
			/*
			stmt.executeUpdate("INSERT INTO `EPM`.`BUILDTASKDETAIL` (`runid`, `poolname`, `javabuild`, `xslbuild`, `timestamp`, `projectid`, `requestid`, `buildid`, " +
					"`status`, `train`, `attempt`, `jiraid`) " +
					"VALUES ("+ runid +",'" + poolname + "'," + javabuildid +"," + xslbuildid +",'"+ timestamp +"',"+ 0 +","+ 0 + ","+ 0 +",'" + status + "',"+ 
					train +"," + attempt +",'"+ jiraid +"')");
		 */
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//ConnectionProvider.close(stmt);
			ConnectionProvider.close(conn);
		}
		
	}
	public static void setXslInitData(BuildTO bsd) {
		Connection conn = new ConnectionProvider().connection();
		PreparedStatement preparedStatement = null;
		int runid = bsd.getRunid();
		long xslbuildid = bsd.getXslBuildId();
		String status = bsd.getStatus();
		int train = bsd.getTrainId();
		int attempt = bsd.getAttempt();
		String jiraid = "None";
		
		System.out.println("runid is "+ runid);

        String insertTableSQL = "INSERT INTO EPM.XSLBUILDTASKDETAIL (runid, xslbuild, status, projectid, requestid, buildid, train, attempt, jiraid) " +
        		"VALUES (?,?,?,?,?,?,?,?,?)";
		try {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
			//stmt = conn.createStatement();
			preparedStatement = conn.prepareStatement(insertTableSQL);
			
			preparedStatement.setInt(1, runid);
			preparedStatement.setLong(2, xslbuildid);
			preparedStatement.setString(3, status);
			preparedStatement.setLong(4, 0);
			preparedStatement.setLong(5, 0);
			preparedStatement.setLong(6, 0);
			preparedStatement.setInt(7, train);
			preparedStatement.setInt(8, attempt);
			preparedStatement.setString(9, jiraid);
			
			preparedStatement .executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			//ConnectionProvider.close(stmt);
			ConnectionProvider.close(conn);
		}
		
	}
	
	public static void main (String [] args) {
		//BuildTO bto = new BuildTO(457, "v3syicore", 123454, 345454, "2014-05-15 12:42:10", "INIT", 875, 0);
		//setInitData(bto);	
		BuildTO bto = new BuildTO(457, 345454, "INIT", 875, 0);
		setXslInitData(bto);
	}
}
