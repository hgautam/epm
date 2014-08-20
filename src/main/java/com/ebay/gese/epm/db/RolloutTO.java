package com.ebay.gese.epm.db;

public class RolloutTO {
	private int runid;
	private String poolname;
	private String ppPoolname;
	private String status;
	private long javabuild;
	private long xslbuild;
	private long tcrollid;
	private int train;
	private int attempt;
	private String jira;
	
	
	public RolloutTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RolloutTO(int runid, String poolname, String status, long javabuild,
			long xslbuild, long tcrollid, int train, int attempt, String jira) {
		super();
		this.runid = runid;
		this.poolname = poolname;
		this.status = status;
		this.javabuild = javabuild;
		this.xslbuild = xslbuild;
		this.tcrollid = tcrollid;
		this.train = train;
		this.attempt = attempt;
		this.jira = jira;
	}

	public int getRunid() {
		return runid;
	}

	public void setRunid(int runid) {
		this.runid = runid;
	}

	public String getPoolname() {
		return poolname;
	}

	public void setPoolname(String poolname) {
		this.poolname = poolname;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getJavabuild() {
		return javabuild;
	}

	public void setJavabuild(long javabuild) {
		this.javabuild = javabuild;
	}

	public long getXslbuild() {
		return xslbuild;
	}

	public void setXslbuild(long xslbuild) {
		this.xslbuild = xslbuild;
	}

	public int getTrain() {
		return train;
	}

	public void setTrain(int train) {
		this.train = train;
	}

	public int getAttempt() {
		return attempt;
	}

	public void setAttempt(int attempt) {
		this.attempt = attempt;
	}

	public String getJira() {
		return jira;
	}

	public void setJira(String jira) {
		this.jira = jira;
	}

	public long getTcrollid() {
		return tcrollid;
	}

	public void setTcrollid(long tcrollid) {
		this.tcrollid = tcrollid;
	}

	public String getPpPoolname() {
		return ppPoolname;
	}

	public void setPpPoolname(String ppPoolname) {
		this.ppPoolname = ppPoolname;
	}
	
	
}

