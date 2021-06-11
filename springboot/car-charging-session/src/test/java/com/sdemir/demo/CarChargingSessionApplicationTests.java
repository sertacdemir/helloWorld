package com.sdemir.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.sdemir.demo.car.charging.session.CarChargingSessionService;
import com.sdemir.demo.car.charging.session.entity.CarChargingSessionEntity;
import com.sdemir.demo.car.charging.session.entity.CarChargingSessionInputEntity;
import com.sdemir.demo.car.charging.session.entity.CarChargingSessionSummaryEntity;
import com.sdemir.demo.car.charging.session.entity.StatusEnum;

@SpringBootTest
@AutoConfigureMockMvc
class CarChargingSessionApplicationTests {
	
	@Autowired
	CarChargingSessionService carChargingSessionService;
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void contextLoads() {
	}
	
	@Test
	void carSessionTestService() {
		
		CarChargingSessionInputEntity input = new CarChargingSessionInputEntity("ABC-80801");
		
		CarChargingSessionEntity result = carChargingSessionService.addChargingEntity(input);
		
		assertThat(result.getStationId().equals(input.getStationId()));
		assertThat(result.getStatus().equals(StatusEnum.IN_PROGRESS));
		
		result = carChargingSessionService.stopChargingSession(result.getId());
		assertThat(result.getStationId().equals(input.getStationId()));
		assertThat(result.getStatus().equals(StatusEnum.FINISHED));		
		
	}
	
	@Test
	void carSessionTestREST() throws Exception {		
		
//		Test Get all charging sessions
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/chargingSessions")
			      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		CarChargingSessionEntity[] entityList = mapFromJson(content, CarChargingSessionEntity[].class);
		assertTrue(entityList.length == 1);
		
//		Test create new charging session
		CarChargingSessionEntity entity = new CarChargingSessionEntity(null, "ABC-80802", null, null, null);
		String inputJson = mapToJson(entity);
		mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/chargingSessions")
			      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		content = mvcResult.getResponse().getContentAsString();
		CarChargingSessionEntity entityResponse = mapFromJson(content, CarChargingSessionEntity.class);
		assertThat(entityResponse.getStationId().equals(entity.getStationId()));
		assertThat(entityResponse.getStatus().equals(StatusEnum.IN_PROGRESS));
		
		
//		Test stop charging session
		String uri = "/chargingSessions/" + entityResponse.getId();
		mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(uri)
			      .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		content = mvcResult.getResponse().getContentAsString();
		entityResponse = mapFromJson(content, CarChargingSessionEntity.class);
		assertThat(entityResponse.getStatus().equals(StatusEnum.FINISHED));
		
//		Test Get summary
		mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/chargingSessions/summary")
			      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		content = mvcResult.getResponse().getContentAsString();
		CarChargingSessionSummaryEntity summaryEntity = mapFromJson(content, CarChargingSessionSummaryEntity.class);
		assertTrue(summaryEntity.getStoppedCount() == 2);
	
	}
	
	protected <T> T mapFromJson(String json, Class<T> clazz)
		      throws JsonParseException, JsonMappingException, IOException {
		      
		      ObjectMapper objectMapper = new ObjectMapper();
		      objectMapper.registerModule(new JSR310Module());
		      objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		      return objectMapper.readValue(json, clazz);
		      }
	
	protected String mapToJson(Object obj) throws JsonProcessingException {
	      ObjectMapper objectMapper = new ObjectMapper();
	      return objectMapper.writeValueAsString(obj);
	      }
}
