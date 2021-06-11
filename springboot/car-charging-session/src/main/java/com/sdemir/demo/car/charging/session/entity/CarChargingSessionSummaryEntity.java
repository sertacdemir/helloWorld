package com.sdemir.demo.car.charging.session.entity;

/**
 * This entry is used for storing summary data for chargin sessions
 * 
 * @author sertacdemir
 * @since 1.0
 *
 */

public class CarChargingSessionSummaryEntity {
	
	private int totalCount;
	private int startedCount;
	private int stoppedCount;
	
	public CarChargingSessionSummaryEntity(int totalCount, int startedCount, int stoppedCount) {
		super();
		this.totalCount = totalCount;
		this.startedCount = startedCount;
		this.stoppedCount = stoppedCount;
	}
	
	public CarChargingSessionSummaryEntity() {
		super();
	}

	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getStartedCount() {
		return startedCount;
	}
	public void setStartedCount(int startedCount) {
		this.startedCount = startedCount;
	}
	public int getStoppedCount() {
		return stoppedCount;
	}
	public void setStoppedCount(int stoppedCount) {
		this.stoppedCount = stoppedCount;
	} 
	
	

}
