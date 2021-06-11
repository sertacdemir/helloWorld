package com.sdemir.demo.car.charging.session.entity;

import javax.validation.constraints.NotEmpty;

/**
 * This is the main entity that we are using to accept post request for creating charging session
 * 
 * @author sertacdemir
 * @since 1.0
 *
 */

public class CarChargingSessionInputEntity {
	
	@NotEmpty(message = "Please provide a Station Id")
	private String stationId;
	

	public CarChargingSessionInputEntity() {
		super();
	}

	public CarChargingSessionInputEntity(@NotEmpty(message = "Please provide a Station Id") String stationId) {
		super();
		this.stationId = stationId;
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}
	
	

}
