package com.ebay.gese.epm.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class InsertRolloutData {
	public static void insertRollout(RolloutTO rto) {
		Connection conn = new ConnectionProvider().connection();
		Statement stmt = null;
		int runid = rto.getRunid();
		String poolname = rto.getPoolname();
		String status = "INIT";
		long javabuild = rto.getJavabuild();
		long xslbuild = 0;
		int train = rto.getTrain();
		int attempt = rto.getAttempt();
		String jira = "None";

		try {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
			stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO `EPM`.`rollouttask` (`runid`, `poolname`, `status`, `javabuild`, `xslbuild`, `train`, `attempt`, `jira`) " +
					"VALUES ("+ runid +",'" + poolname + "','" + status +"',"+ javabuild +","+ xslbuild +","+ train + ","+ attempt +",'"+ jira +"')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionProvider.close(stmt);
			ConnectionProvider.close(conn);
		}
	}
	
	public static void updateXslRollout(int runid, long buildid) {
		Connection conn = new ConnectionProvider().connection();
		Statement stmt = null;
		String status = "ReadyToStart";
		try {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
			stmt = conn.createStatement();
			stmt.executeUpdate("update `EPM`.`rollouttask` SET xslbuild ="+ buildid + " , status = '"+ status + "' where runid ="+ runid +" and status = 'INIT'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionProvider.close(stmt);
			ConnectionProvider.close(conn);
		}
	}
	
	public static void updateRollout(RolloutTO rto) {
		Connection conn = new ConnectionProvider().connection();
		Statement stmt = null;
		int runid = rto.getRunid();
		long tcrollid = rto.getTcrollid();
		String status = rto.getStatus();
		String poolname = rto.getPoolname();
		int attempt = rto.getAttempt();
		try {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
			stmt = conn.createStatement();
			stmt.executeUpdate("update `EPM`.`rollouttask` SET status ='"+ status + "', tcrollid ="+ tcrollid + ", attempt =" + attempt +" where poolname = '"+ poolname +"' and runid ="+ runid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionProvider.close(stmt);
			ConnectionProvider.close(conn);
		}
	}
	
	public static void updateRollout(long tcrollid, String status) {
		Connection conn = new ConnectionProvider().connection();
		Statement stmt = null;

		try {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
			stmt = conn.createStatement();
			stmt.executeUpdate("update `EPM`.`rollouttask` SET status ='"+ status + "' where tcrollid ="+ tcrollid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionProvider.close(stmt);
			ConnectionProvider.close(conn);
		}
	}
	
	public static void main(String args []) {
		RolloutTO rto = new RolloutTO(531, "v3syicore", "INIT", 123232, 34353, 99564, 879, 1, "None" );
		//insertRollout(rto);
		updateXslRollout(531, 12324);
	}

}
