package com.sdemir.demo.car.charging.session.entity;

import java.time.LocalDateTime;
import java.util.UUID;


/**
 * This is the main entity that we are using 
 * 
 * @author sertacdemir
 * @since 1.0
 *
 */

public class CarChargingSessionEntity {
	
	
	private UUID id;
	private String stationId;
	private LocalDateTime startedAt;
	private LocalDateTime stoppedAt;
	StatusEnum status;
	
	
	public CarChargingSessionEntity(UUID id, String stationId, LocalDateTime startedAt, LocalDateTime stoppedAt,
			StatusEnum status) {
		super();
		this.id = id;
		this.stationId = stationId;
		this.startedAt = startedAt;
		this.stoppedAt = stoppedAt;
		this.status = status;
	}
	
	public CarChargingSessionEntity() {
		super();
	}

	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getStationId() {
		return stationId;
	}
	public void setStationId(String stationId) {
		this.stationId = stationId;
	}
	public LocalDateTime getStartedAt() {
		return startedAt;
	}
	public void setStartedAt(LocalDateTime startedAt) {
		this.startedAt = startedAt;
	}
	public LocalDateTime getStoppedAt() {
		return stoppedAt;
	}
	public void setStoppedAt(LocalDateTime stoppedAt) {
		this.stoppedAt = stoppedAt;
	}
	public StatusEnum getStatus() {
		return status;
	}
	public void setStatus(StatusEnum status) {
		this.status = status;
	}
	
	
	

}
