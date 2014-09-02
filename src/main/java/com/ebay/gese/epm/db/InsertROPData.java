package com.ebay.gese.epm.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

public class InsertROPData {
	final static Logger logger = Logger.getLogger(InsertROPData.class);
	public static void insertRecord(RopTO rop) {
		Connection conn = new ConnectionProvider().connection();
		Statement stmt = null;
		int trainid = rop.getTrainid();
		ArrayList<String> poolList = rop.getPoolNames();
		try {
			for (String poolName : poolList) {
				//System.out.println(poolName);
				logger.debug(poolName);
				stmt = conn.createStatement();
				stmt.executeUpdate("INSERT INTO `EPM`.`ROP` (`poolname`, `train`) VALUES ('"
						+ poolName + "'," + trainid + ")");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error(e);
		} finally {
			ConnectionProvider.close(stmt);
			ConnectionProvider.close(conn);
		}
	}
}
