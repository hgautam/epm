package com.ebay.gese.epm.db;

import java.util.ArrayList;

public class RopTO {
	private int trainid;
	private String poolName;
	private ArrayList<String> poolList;
	
	public RopTO() {
		super();
		//poolList = new ArrayList<String>();
		// TODO Auto-generated constructor stub
	}
	
	public RopTO(int trainid, ArrayList<String> poolList) {
		super();
		this.trainid = trainid;
		this.poolList = poolList;
	}

	public int getTrainid() {
		return trainid;
	}

	public void setTrainid(int trainid) {
		this.trainid = trainid;
	}

	public String getPoolName() {
		return poolName;
	}

	public void setPoolName(String poolName) {
		this.poolName = poolName;
	}
	public void addPoolName(String poolName) {
		poolList.add(poolName);
	}
	
	public ArrayList<String> getPoolNames() {
		return poolList;
	}
	
}
