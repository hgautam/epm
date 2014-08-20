package com.ebay.gese.epm.db;



public class BuildTO {
	private String poolName;
	private int runid;
	private long javaBuildId;
	private long xslBuildId;
	private String timeStamp;
	private String status;
	private long projectId;
	private long requestId;
	private long buildId;
	private int trainId;
	private int attempt;
	
	public BuildTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public BuildTO(int runid, String poolName, long javaBuildId, long xslBuildId, String timeStamp, String status,
			int trainId, int attempt) {
		this.runid = runid;
		this.poolName = poolName;
		this.javaBuildId = javaBuildId;
		this.xslBuildId = xslBuildId;
		this.timeStamp = timeStamp;
		this.status = status;
		this.trainId = trainId;
		this.attempt = attempt;
		
	}
	
	public BuildTO(int runid, long xslBuildId, String status, int trainId, int attempt) {
		this.runid = runid;
		this.xslBuildId = xslBuildId;
		this.status = status;
		this.trainId = trainId;
		this.attempt = attempt;
		
	}

	public String getPoolName() {
		return poolName;
	}

	public void setPoolName(String poolName) {
		this.poolName = poolName;
	}

	public int getRunid() {
		return runid;
	}

	public void setRunid(int runid) {
		this.runid = runid;
	}

	public long getJavaBuildId() {
		return javaBuildId;
	}

	public void setJavaBuildId(long javaBuildId) {
		this.javaBuildId = javaBuildId;
	}

	public long getXslBuildId() {
		return xslBuildId;
	}

	public void setXslBuildId(long xslBuildId) {
		this.xslBuildId = xslBuildId;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	public long getRequestId() {
		return requestId;
	}

	public void setRequestId(long requestId) {
		this.requestId = requestId;
	}

	public long getBuildId() {
		return buildId;
	}

	public void setBuildId(long buildId) {
		this.buildId = buildId;
	}

	public int getTrainId() {
		return trainId;
	}

	public void setTrainId(int trainId) {
		this.trainId = trainId;
	}

	public int getAttempt() {
		return attempt;
	}

	public void setAttempt(int attempt) {
		this.attempt = attempt;
	}


	

}
