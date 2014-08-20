package com.ebay.gese.epm.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class RolloutDAO {
	public static ArrayList<RolloutTO> getPoolsToRoll(int runid) {
		Connection conn = new ConnectionProvider().connection();
		String sql = ("SELECT javabuild, xslbuild, poolname, train, attempt from rollouttask where RUNID =" + runid + " and status in ('ReadyToStart') and attempt < 3");
		Statement stmt = null;
		ResultSet rs = null;
		String poolname = null;
		String ppPoolname = null;
		long javabuild = 0;
		long xslbuild = 0;
		int train = 0;
		int attempt = 0;
		ArrayList<RolloutTO> al = new ArrayList<RolloutTO>();
		try {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				RolloutTO rto = new RolloutTO();
				javabuild   = rs.getLong("javabuild");
				xslbuild   = rs.getLong("xslbuild");
				poolname   = rs.getString("poolname");
				train   = rs.getInt("train");
				attempt = rs.getInt("attempt");
				
				//get ppPoolname now
				//ppPoolname = PoolDAO.getppPoolname(poolname);
				rto.setPoolname(poolname);
				//rto.setPpPoolname(ppPoolname);
				rto.setJavabuild(javabuild);
				rto.setXslbuild(xslbuild);
				rto.setTrain(train);
				rto.setAttempt(attempt);
				al.add(rto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionProvider.close(rs, stmt);
			ConnectionProvider.close(conn);
		}
		return al;
	}
	
	public static ArrayList<RolloutTO> getPoolsToMonitor(int runid) {
		Connection conn = new ConnectionProvider().connection();
		String sql = ("SELECT javabuild, xslbuild, poolname, train, attempt, tcrollid from rollouttask where RUNID =" + runid + " and status not in ('All Done', 'Cancelled', 'Detached') and attempt < 3");
		Statement stmt = null;
		ResultSet rs = null;
		String poolname = null;
		String ppPoolname = null;
		int tcrollid = 0;
		long javabuild = 0;
		long xslbuild = 0;
		int train = 0;
		int attempt = 0;
		ArrayList<RolloutTO> al = new ArrayList<RolloutTO>();
		try {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				RolloutTO rto = new RolloutTO();
				javabuild   = rs.getLong("javabuild");
				xslbuild   = rs.getLong("xslbuild");
				poolname   = rs.getString("poolname");
				train   = rs.getInt("train");
				attempt = rs.getInt("attempt");
				tcrollid = rs.getInt("tcrollid");
				
				
				//get ppPoolname now
				//ppPoolname = PoolDAO.getppPoolname(poolname);
				rto.setPoolname(poolname);
				rto.setPpPoolname(poolname);
				rto.setJavabuild(javabuild);
				rto.setXslbuild(xslbuild);
				rto.setTrain(train);
				rto.setAttempt(attempt);
				rto.setTcrollid(tcrollid);
				al.add(rto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionProvider.close(rs, stmt);
			ConnectionProvider.close(conn);
		}
		return al;
	}
	
	public static boolean entryExists(int runid, String ppName) {
		Connection conn = new ConnectionProvider().connection();
		String sql = ("SELECT count(*) as result from rollouttask where RUNID =" + runid + " and poolname = '"+ ppName +"'");
		Statement stmt = null;
		ResultSet rs = null;
		boolean result = true;
		int outcome = 0;

		try {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				outcome = rs.getInt("result");
				if(outcome == 0) {
					result = false;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionProvider.close(rs, stmt);
			ConnectionProvider.close(conn);
		}
		return result;
	}
	
	public static boolean xslEntryExists(int runid, long buildid) {
		Connection conn = new ConnectionProvider().connection();
		String sql = ("SELECT count(*) as result from rollouttask where RUNID =" + runid + " and xslbuild = "+ buildid );
		Statement stmt = null;
		ResultSet rs = null;
		boolean result = true;
		int outcome = 0;

		try {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				outcome = rs.getInt("result");
				if(outcome == 0) {
					result = false;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionProvider.close(rs, stmt);
			ConnectionProvider.close(conn);
		}
		return result;
	}
	
	
	
	public static void main (String [] args) {
		System.out.println("This entry is "+ entryExists(538, "v3feedbackppcore1"));
		System.out.println("This entry is "+ xslEntryExists(538, 16898021));
		/*
		ArrayList<RolloutTO> al = getPoolsToMonitor(531);
		
		for (RolloutTO rto: al) {
			System.out.println(rto.getPpPoolname());
			System.out.println(rto.getJavabuild());
			System.out.println(rto.getXslbuild());
			System.out.println(rto.getTrain());
			System.out.println(rto.getAttempt());
		} */
	}

}
