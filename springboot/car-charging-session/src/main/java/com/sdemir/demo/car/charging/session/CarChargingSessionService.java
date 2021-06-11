package com.sdemir.demo.car.charging.session;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sdemir.demo.car.charging.session.entity.CarChargingSessionEntity;
import com.sdemir.demo.car.charging.session.entity.CarChargingSessionInputEntity;
import com.sdemir.demo.car.charging.session.entity.CarChargingSessionSummaryEntity;
import com.sdemir.demo.car.charging.session.entity.StatusEnum;
import com.sdemir.demo.car.charging.session.exception.CustomException;
import com.sdemir.demo.car.charging.session.exception.ResourceNotFoundException;

/**
 * This is service is implemented to fulfill needs for REST APIs
 * 
 * @author sertacdemir
 * @since 1.0
 *
 */

@Service
public class CarChargingSessionService {
	
	private static final Logger LOG = LoggerFactory.getLogger(CarChargingSessionService.class);
	
//	Atomic integer is used for thread safety for counter
//	Counters are used to match computing complexity
	AtomicInteger startedChargingEntity = new AtomicInteger();
	AtomicInteger stoppedChargingEntity = new AtomicInteger();
	
//	Use Concurrent Hash map for complexity and thread safe
	private Map<UUID, CarChargingSessionEntity> chargingEntities = new ConcurrentHashMap<>();
	
	
	/**
	 * This function returns all the entities
	 * @return all car charging session entities
	 */
	public Collection<CarChargingSessionEntity> getAllChargingEntities() {
		LOG.info("Get all charging entitties request is received.");
		return chargingEntities.values();
	}
	
	/**
	 * This function adds a new entity 
	 * @param input can not be null
	 * @return returns the created entity.  
	 */
	public CarChargingSessionEntity addChargingEntity(CarChargingSessionInputEntity input) {
		LOG.info("Add new charging entity request is received.");
		if (input.getStationId() == null || input.getStationId().isEmpty()) {
			throw new CustomException("You should send a message with stationId ");
		} else {
			CarChargingSessionEntity entity = new CarChargingSessionEntity(UUID.randomUUID(),input.getStationId(),LocalDateTime.now(), null, StatusEnum.IN_PROGRESS);
			chargingEntities.put(entity.getId(), entity);
			startedChargingEntity.incrementAndGet();
			LOG.info("New charging entity is saved with id: {}", entity.getId());
			return entity;
		}
	}
	
	/**
	 * Thus function stops the car charging session if it is running
	 * @param id
	 * @return the changed entity 
	 */
	public CarChargingSessionEntity stopChargingSession(UUID id) {
		LOG.info("Stop charging entity request is received.");
//		To make log(n) 
		CarChargingSessionEntity entity = chargingEntities.get(id);
		if (entity == null) {
			LOG.info("No charging station is found with id: {}", id);
			throw new ResourceNotFoundException("Requested resource is not found with id: " + id);
		} else if (entity.getStatus().equals(StatusEnum.IN_PROGRESS)) {
				stoppedChargingEntity.incrementAndGet();
				startedChargingEntity.decrementAndGet();
				entity.setStatus(StatusEnum.FINISHED);
				entity.setStoppedAt(LocalDateTime.now());
				LOG.info("Charging entity with id: {} is stopped successfully!", id);
				return entity;
		} else {
			LOG.info("Charging statis is already stopped with id: {}", id);
			throw new CustomException("Charging station is already stopped with id: " + id);
		}
		
		
	}
	
	/**
	 * This function returns the summary of the sessions with details of running and stopped ones
	 * @return the summary of statuses and total number of entities
	 */
	public CarChargingSessionSummaryEntity retrieveSummary() {
		LOG.info("Retrieve summary request is received.");		
		CarChargingSessionSummaryEntity summary = new CarChargingSessionSummaryEntity(startedChargingEntity.get() + stoppedChargingEntity.get(), startedChargingEntity.get(), stoppedChargingEntity.get());
		LOG.info("Summory of the charging session is calculated! ");
		return summary;
	}

}
