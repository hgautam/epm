package com.ebay.gese.epm.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class InsertROPData {
	public static void insertRecord(RopTO rop) {
		Connection conn = new ConnectionProvider().connection();
		Statement stmt = null;
		int trainid = rop.getTrainid();
		ArrayList<String> poolList = rop.getPoolNames();
		try {
			for (String poolName : poolList) {
				System.out.println(poolName);
				stmt = conn.createStatement();
				stmt.executeUpdate("INSERT INTO `EPM`.`ROP` (`poolname`, `train`) VALUES ('"
						+ poolName + "'," + trainid + ")");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			ConnectionProvider.close(stmt);
			ConnectionProvider.close(conn);
		}
	}
}
