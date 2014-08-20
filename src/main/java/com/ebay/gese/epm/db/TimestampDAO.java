package com.ebay.gese.epm.db;
/*
 * This should return the last record in the config spec table
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class TimestampDAO {
	private String tStamp;
	
//	public String getTstamp() {
//		return tStamp;
//	}
	
	public Timestamp getTstamp() {
		Connection conn = new ConnectionProvider().connection();
		String sql = ("SELECT timestamp FROM caboose_timestamp where ID = (SELECT MAX(ID) FROM caboose_timestamp)");
		Statement stmt = null;
		ResultSet rs = null;
		Timestamp tStamp = null;
		try {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				//tStamp = rs.getString("timestamp");
				  tStamp   = rs.getTimestamp("timestamp");
				System.out.println("timestamp is " + tStamp);
			}
			//return tStamp;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionProvider.close(rs, stmt);
			ConnectionProvider.close(conn);
		}
		return tStamp;
	}
	
	public static void main (String [] args) {
		TimestampDAO ts = new TimestampDAO();
		//String time = ts.getTstamp();
		System.out.println("Time is "+ ts.getTstamp());
		
	}

}
