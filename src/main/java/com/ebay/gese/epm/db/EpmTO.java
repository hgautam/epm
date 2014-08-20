package com.ebay.gese.epm.db;

import java.sql.Timestamp;

public class EpmTO {
	private int runid;
	private String locale;
	private Timestamp time;
	private String environment;
	
	public EpmTO() {
		super();
	}

	public EpmTO(String locale, Timestamp time, String environment) {
		super();
		this.locale = locale;
		this.time = time;
		this.environment = environment;
	}

	public int getRunid() {
		return runid;
	}

	public void setRunid(int runid) {
		this.runid = runid;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}
}
