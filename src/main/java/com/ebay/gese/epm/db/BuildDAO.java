package com.ebay.gese.epm.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.apache.log4j.Logger;

public class BuildDAO {
	final static Logger logger = Logger.getLogger(BuildDAO.class);
	public static boolean recordExists(String poolName, int trainid) {
		Connection conn = new ConnectionProvider().connection();
		String sql = ("SELECT count(*) FROM buildtaskdetail where train ="+ trainid +" and poolname ='"+ poolName +"'");
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
			//e.printStackTrace();
			logger.error(e);
		} finally {
			ConnectionProvider.close(rs, stmt);
			ConnectionProvider.close(conn);
		}
		if (count == 0) {
			return false;
		} else {
			return false;
		}
	}
	
	public static Timestamp getTimestamp(String poolName, int trainid) {
		Connection conn = new ConnectionProvider().connection();
		String sql = ("SELECT timestamp FROM buildtaskdetail where train ="+ trainid +" and poolname ='"+ poolName +"' order by runid DESC LIMIT 1");
		Statement stmt = null;
		ResultSet rs = null;
		Timestamp tstamp = null;
		try {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				tstamp = rs.getTimestamp(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error(e);
		} finally {
			ConnectionProvider.close(rs, stmt);
			ConnectionProvider.close(conn);
		}
		return tstamp;

	}
	
	public static Timestamp getTimestamp(String poolName) {
		Connection conn = new ConnectionProvider().connection();
		String sql = ("SELECT timestamp FROM epm.buildtaskdetail where poolname = '"+ poolName +"' order by runid DESC LIMIT 1");
		Statement stmt = null;
		ResultSet rs = null;
		Timestamp tstamp = null;
		try {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				tstamp = rs.getTimestamp(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error(e);
		} finally {
			ConnectionProvider.close(rs, stmt);
			ConnectionProvider.close(conn);
		}
		return tstamp;

	}
	
	public static long getXslBuild(int runid, int train) {
		Connection conn = new ConnectionProvider().connection();
		String sql = ("SELECT distinct xslbuild FROM buildtaskdetail where runid ="+ runid +" and train ='"+ train +"'");
		Statement stmt = null;
		ResultSet rs = null;
		long buildid = 0;
		try {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			rs.last();
            int totalRows = rs.getRow();
            rs.beforeFirst();
			if (totalRows > 1) {
				//System.out.println("Getting more than one xsl builds for this run. No op");
				logger.debug("Getting more than one xsl builds for this run. No op");
			} else {
				while (rs.next()) {
					buildid = rs.getLong(1);
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error(e);
		} finally {
			ConnectionProvider.close(rs, stmt);
			ConnectionProvider.close(conn);
		}
		return buildid;

	}
	
	public static long getBuildId(long projectid, long requestid) {
		Connection conn = new ConnectionProvider().connection();
		String sql = ("SELECT buildid FROM buildtaskdetail where projectid ="+ projectid +" and requestid ="+ requestid);
		Statement stmt = null;
		ResultSet rs = null;
		long buildid = 0;
		try {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				buildid = rs.getLong(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error(e);
		} finally {
			ConnectionProvider.close(rs, stmt);
			ConnectionProvider.close(conn);
		}
		return buildid;

	}
	
	public static long getXslBuildId(long projectid, long requestid) {
		Connection conn = new ConnectionProvider().connection();
		String sql = ("SELECT buildid FROM xslbuildtaskdetail where projectid ="+ projectid +" and requestid ="+ requestid);
		Statement stmt = null;
		ResultSet rs = null;
		long buildid = 0;
		try {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				buildid = rs.getLong(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error(e);
		} finally {
			ConnectionProvider.close(rs, stmt);
			ConnectionProvider.close(conn);
		}
		return buildid;

	}
	
	public static int getCurrentTrain(int runid) {
		Connection conn = new ConnectionProvider().connection();
		String sql = ("SELECT distinct train from buildtaskdetail where RUNID =" + runid);
		Statement stmt = null;
		ResultSet rs = null;
		int train = -1;
		try {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				  train   = rs.getInt("train");
				System.out.println("train is " + train);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error(e);
		} finally {
			ConnectionProvider.close(rs, stmt);
			ConnectionProvider.close(conn);
		}
		return train;
	}
	
	public static ArrayList<BuildTO> getPoolsToBuild(int runid) {
		Connection conn = new ConnectionProvider().connection();
		String sql = ("SELECT poolname, javabuild, attempt from buildtaskdetail where RUNID =" + runid + " and status in ('INIT','DEPLOY_FAILED','BUILD_FAILED') and attempt < 3");
		Statement stmt = null;
		ResultSet rs = null;
		String poolname = null;
		long buildid = 0;
		int attempt = 0;
		ArrayList<BuildTO> al = new ArrayList<BuildTO>();
		try {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				BuildTO bto = new BuildTO();
				poolname   = rs.getString("poolname");
				//System.out.println("poolname is " + poolname);
				buildid   = rs.getLong("javabuild");
				//System.out.println("buildid is " + buildid);
				attempt   = rs.getInt("attempt");
				bto.setPoolName(poolname);
				bto.setJavaBuildId(buildid);
				bto.setAttempt(attempt);
				al.add(bto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error(e);
		} finally {
			ConnectionProvider.close(rs, stmt);
			ConnectionProvider.close(conn);
		}
		return al;
	}
	
	public static ArrayList<BuildTO> getFailedBuilds(int runid) {
		Connection conn = new ConnectionProvider().connection();
		String sql = ("SELECT poolname from buildtaskdetail where RUNID =" + runid + " and status = 'BUILD_FAILED' and attempt = 3 and jiraid = 'None'");
		Statement stmt = null;
		ResultSet rs = null;
		String poolname = null;
		ArrayList<BuildTO> al = new ArrayList<BuildTO>();
		try {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				BuildTO bto = new BuildTO();
				poolname   = rs.getString("poolname");
				bto.setPoolName(poolname);
				al.add(bto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error(e);
		} finally {
			ConnectionProvider.close(rs, stmt);
			ConnectionProvider.close(conn);
		}
		return al;
	}
	
	public static long getFailedXslBuild(int runid) {
		Connection conn = new ConnectionProvider().connection();
		String sql = ("SELECT xslbuild from xslbuildtaskdetail where RUNID =" + runid + " and status = 'BUILD_FAILED' and attempt = 3 and jiraid = 'None'");
		Statement stmt = null;
		ResultSet rs = null;
		long xslbuild = 0;
		try {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				xslbuild = rs.getLong("xslbuild");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error(e);
		} finally {
			ConnectionProvider.close(rs, stmt);
			ConnectionProvider.close(conn);
		}
		return xslbuild;
	}
	
	
	public static ArrayList<BuildTO> getPoolsToMonitor(int runid) {
		Connection conn = new ConnectionProvider().connection();
		String sql = ("SELECT projectid, requestid from buildtaskdetail where RUNID =" + runid + " and status not in ('DEPLOY_COMPLETED','DEPLOY_SUCCESS','INIT','BUILD_FAILED')");
		Statement stmt = null;
		ResultSet rs = null;
		//String poolname = null;
		long projectid = 0;
		long requestid = 0;
		ArrayList<BuildTO> al = new ArrayList<BuildTO>();
		try {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				BuildTO bto = new BuildTO();
				projectid   = rs.getLong("projectid");
				//System.out.println("projectid is " + projectid);
				requestid   = rs.getLong("requestid");
				//System.out.println("requestid is " + requestid);
				//bto.setPoolName(poolname);
				bto.setProjectId(projectid);
				bto.setRequestId(requestid);
				al.add(bto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error(e);
		} finally {
			ConnectionProvider.close(rs, stmt);
			ConnectionProvider.close(conn);
		}
		//System.out.println("al size is "+ al.size());
		return al;
	}
	
	public static ArrayList<BuildTO> getPoolsToRoll(int runid) {
		Connection conn = new ConnectionProvider().connection();
		String sql = ("SELECT poolname, buildid from buildtaskdetail where RUNID =" + runid + " and status in ('DEPLOY_COMPLETED','DEPLOY_SUCCESS')");
		Statement stmt = null;
		ResultSet rs = null;
		String poolname = null;
		long buildid = 0;
		//long requestid = 0;
		ArrayList<BuildTO> al = new ArrayList<BuildTO>();
		try {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				BuildTO bto = new BuildTO();
				poolname   = rs.getString("poolname");
				//System.out.println("poolname is " + poolname);
				buildid   = rs.getLong("buildid");
				String ppPoolname = PoolDAO.getppPoolname(poolname);
				if (ppPoolname == null) {
				    	//System.out.println("No prepod pool name found for "+ poolname);
				    	logger.debug("No prepod pool name found for "+ poolname);
				    	continue;
				} else {
					//check if this pool name is already in rollouttask table for this runid
					if (RolloutDAO.entryExists(runid, ppPoolname)) {
						//System.out.println("Rollout for pool "+ poolname + " is already in progress.");
						logger.debug("Rollout for pool "+ poolname + " is already in progress.");
					} else {
						bto.setPoolName(poolname);
						bto.setBuildId(buildid);
						al.add(bto);
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error(e);
		} finally {
			ConnectionProvider.close(rs, stmt);
			ConnectionProvider.close(conn);
		}
		return al;
	}
	
	public static long getXslToRoll(int runid) {
		Connection conn = new ConnectionProvider().connection();
		String sql = ("SELECT buildid from xslbuildtaskdetail where RUNID =" + runid + " and status in ('DEPLOY_COMPLETED','DEPLOY_SUCCESS')");
		Statement stmt = null;
		ResultSet rs = null;
		long buildid = 0;
		

		try {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				buildid   = rs.getLong("buildid");
				//xsl build done for a runid should be used for multiple deploys of that runid
				/*
				if (RolloutDAO.xslEntryExists(runid, buildid)) {
					buildid = 0;
					return buildid;
				}
				*/
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error(e);
		} finally {
			ConnectionProvider.close(rs, stmt);
			ConnectionProvider.close(conn);
		}
		return buildid;
	}
	
	public static BuildTO getXslToMonitor(int runid) {
		Connection conn = new ConnectionProvider().connection();
		String sql = ("SELECT projectid, requestid from xslbuildtaskdetail where RUNID =" + runid + " and status not in ('DEPLOY_COMPLETED','DEPLOY_SUCCESS','BUILD_FAILED')");
		Statement stmt = null;
		ResultSet rs = null;
		//String poolname = null;
		long projectid = 0;
		long requestid = 0;
		BuildTO bto = null;
		ArrayList<BuildTO> al = new ArrayList<BuildTO>();
		try {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				bto = new BuildTO();
				projectid   = rs.getLong("projectid");
				//System.out.println("buildid is " + buildid);
				requestid   = rs.getLong("requestid");
				//bto.setPoolName(poolname);
				bto.setProjectId(projectid);
				bto.setRequestId(requestid);
				al.add(bto);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error(e);
		} finally {
			ConnectionProvider.close(rs, stmt);
			ConnectionProvider.close(conn);
		}
		return bto;
	}
	
	public static BuildTO getXslToBuild(int runid) {
		Connection conn = new ConnectionProvider().connection();
		String sql = ("SELECT xslbuild, attempt from xslbuildtaskdetail where RUNID =" + runid + " and status in ('INIT','DEPLOY_FAILED','BUILD_FAILED') and attempt < 3");
		Statement stmt = null;
		ResultSet rs = null;
		long buildid = 0;
		int attempt = 0;
		BuildTO bto = null;
		try {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				bto = new BuildTO();
				buildid   = rs.getLong("xslbuild");
				//System.out.println("buildid is " + buildid);
				attempt   = rs.getInt("attempt");
				bto.setXslBuildId(buildid);
				bto.setAttempt(attempt);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error(e);
		} finally {
			ConnectionProvider.close(rs, stmt);
			ConnectionProvider.close(conn);
		}
		return bto;
	}
	
	public static void main(String [] args){
		/*
		ArrayList<BuildTO> al = getPoolsToRoll(484);
		for(BuildTO bt: al) {
			System.out.println(bt.getPoolName());
			System.out.println(bt.getBuildId());
		} */
		long xslbuild = getXslToRoll(525);
		System.out.println("xsl build is "+ xslbuild);
		//BuildTO bto = getXslToMonitor(484);
		//System.out.println("proectid is "+ bto.getProjectId());
		//System.out.println("requestid is "+ bto.getRequestId());
		//Timestamp t = getTimestamp("v3apifeedbackcore");
		//System.out.println("time stamp is "+ t.toString());
		
		//long buildid = getBuildId(2918865, 966301);
		//System.out.println("build id is "+ buildid);
		
		//boolean result = recordExists("v3apicatalogcore", 875);
		//System.out.println(result);
		//String stamp = getTimestamp("v3syicore", 457);
		//System.out.println("Time stamp is "+ stamp);
		//long buildid = getXslBuild(479, 875);
		//System.out.println("xsl build is "+ buildid);
		//int train = getCurrentTrain(457);
		//System.out.println("current train is "+ train);
		/*
		ArrayList<BuildTO> al = getPoolsToBuild(457);
		for(BuildTO bt: al) {
			System.out.println(bt.getPoolName());
			System.out.println(bt.getJavaBuildId());
			System.out.println(bt.getAttempt());
		}
		
		ArrayList<BuildTO> al = getPoolsToMonitor(484);
		for(BuildTO bt: al) {
			System.out.println(bt.getProjectId());
			System.out.println(bt.getRequestId());
			//System.out.println(bt.getJavaBuildId());
			//System.out.println(bt.getAttempt());
		}
		//BuildTO bto = getXslToBuild(484);
		//System.out.println(bto.getXslBuildId());
		//System.out.println(bto.getAttempt());
		 * 
		 */
		//long buildid = getXslBuild(486, 877);
		//System.out.println(buildid);
	} 
}
