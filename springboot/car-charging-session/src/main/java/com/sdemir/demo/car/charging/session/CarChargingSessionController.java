package com.sdemir.demo.car.charging.session;

import java.util.Collection;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sdemir.demo.car.charging.session.entity.CarChargingSessionEntity;
import com.sdemir.demo.car.charging.session.entity.CarChargingSessionInputEntity;
import com.sdemir.demo.car.charging.session.entity.CarChargingSessionSummaryEntity;

/**
 * This controller publishes REST APIs that is needed to access
 * 
 * @author sertacdemir
 * @since 1.0
 *
 */

@RestController
public class CarChargingSessionController {
	
	/**
	 * A service auto binding whic fulfills the necessary operations
	 */
	@Autowired
	private CarChargingSessionService carChargingSessionService;
	
	/**
	 * This REST GET resource exposes to API to query all Car Charging Sessions
	 * 
	 * @return all Car Charging Sessions
	 * @since 1.0
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/chargingSessions")
	public Collection<CarChargingSessionEntity> getAllChargingEntities() {
		return carChargingSessionService.getAllChargingEntities();
	}
	
	/**
	 * This REST POST resource is to add a new Car Charging Session
	 * 
	 * @param input Car Charging Session  input with single parameter: stationId
	 * @return entity created to see the created entity
	 * @since 1.0
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/chargingSessions")
	public CarChargingSessionEntity addChargingEntity (@Valid @RequestBody CarChargingSessionInputEntity input) {
		return carChargingSessionService.addChargingEntity(input);
	}
	
	/**
	 * This REST PUT resource is to stop existing Car Charging Session
	 * 
	 * @param id of the Car Charging Session
	 * @return entity stopped to see the details of the entity
	 * @since 1.0
	 */
	@RequestMapping(method = RequestMethod.PUT, value = "/chargingSessions/{id}")
	public CarChargingSessionEntity stopChargingSession(@PathVariable UUID id) {
		return carChargingSessionService.stopChargingSession(id);
	}
	
	/**
	 * This GET REST resource is to retrieve the summary of the numbers of the Car Charging Sessions
	 * 
	 * @return the calculated numbers of the car charging sessions
	 * @since 1.0
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/chargingSessions/summary")
	public CarChargingSessionSummaryEntity retrieveSummary() {
		return carChargingSessionService.retrieveSummary();
		
	}

}
