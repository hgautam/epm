package com.ebay.gese.epm.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import com.ebay.gese.epm.db.ConnectionProvider;

public class DBTest {
	public static void main(String args[]) {
		/*
	      Class.forName("com.mysql.jdbc.Driver");
	      Connection conn = DriverManager.getConnection(
	          "jdbc:mysql://localhost:3306/epm", "root", "admin");
	      Statement stmt = conn.createStatement();
	      ResultSet rs = stmt.executeQuery("select deliverable from build_request");

	      while (rs.next()) {
	         String delName = rs.getString("deliverable");
	         System.out.println("deliverable is " + delName);
	      }

	      conn.close();
	    */
		//ConnectionProvider provider = new ConnectionProvider();
		Connection conn = new ConnectionProvider().connection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();

			rs = stmt.executeQuery("select deliverable from build_request");
			while (rs.next()) {
				String delName = rs.getString("deliverable");
				System.out.println("deliverable is " + delName);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionProvider.close(rs, stmt);
			ConnectionProvider.close(conn);
		}
	} 

}
