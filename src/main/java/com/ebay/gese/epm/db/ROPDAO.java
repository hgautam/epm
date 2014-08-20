package com.ebay.gese.epm.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ROPDAO {
	
	public static boolean getCurrentTrainfromROP(int trainid) {
		Connection conn = new ConnectionProvider().connection();
		String sql = ("SELECT count(*) FROM rop where train ="+ trainid);
		Statement stmt = null;
		ResultSet rs = null;
		int count = -1;
		try {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			rs.next();
			count = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionProvider.close(rs, stmt);
			ConnectionProvider.close(conn);
		}
		
		if (count == 0) {
			return false;
		} else {
			return true;
		}
		//return count;
	}
	
	public static ArrayList<String> getROPlist(int trainid) {
		Connection conn = new ConnectionProvider().connection();
		String sql = ("SELECT poolname FROM rop where train ="+ trainid);
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<String> al = new ArrayList<String>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				al.add(rs.getString("poolname"));
				//System.out.println("Pool name is " + rs.getString("poolname"));
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
	/*
	public static void main (String [] args) {
		int count = getTrainfromROP(875);
		System.out.println("count is "+ count);
	}
   */
}
