package com.sdemir.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This class includes main method which is a starting point of our application
 * 
 * @author sertacdemir
 * @see <a href="http://localhost:8082/v3/api-docs">OpenAPI</a>
 * @see <a href="http://localhost:8082/swagger-ui.html">Swagger</a>
 * @since 1.0
 *
 */

@SpringBootApplication
public class CarChargingSessionApplication {

	
	private static final Logger LOG = LoggerFactory.getLogger(CarChargingSessionApplication.class);
	
	public static void main(String[] args) {
		LOG.info("Application is starting..");
		SpringApplication.run(CarChargingSessionApplication.class, args);
	}

}
