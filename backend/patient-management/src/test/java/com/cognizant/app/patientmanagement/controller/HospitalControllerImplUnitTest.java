package com.cognizant.app.patientmanagement.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cognizant.app.patientmanagement.controller.impl.HospitalControllerImpl;
import com.cognizant.app.patientmanagement.pojo.HospitalPojo;
import com.cognizant.app.patientmanagement.service.HospitalService;
import com.cognizant.app.patientmanagement.utility.MappingJacksonValueBuilder;

@ExtendWith(SpringExtension.class)
@WebMvcTest(HospitalControllerImpl.class)
public class HospitalControllerImplUnitTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private HospitalService hospitalService;

	@Test
	public void retrieveAllHospitalsUnitTest() throws Exception {
		
		HospitalPojo hospitalPojo = new HospitalPojo(10001L, "R.G Kar Medical College and Hospital", "GOV", 5000, true, "B3A7K6Z9R2", null, null);
		
		List<HospitalPojo> hospitals = new ArrayList<>();
		hospitals.add(hospitalPojo);
		
		MappingJacksonValue hospital = MappingJacksonValueBuilder.init(hospitals)
										.addFilter(HospitalPojo.FILTER, "employees", "patients")
										.build();
		
		when(hospitalService.retrieveAllHospitals())
			.thenReturn(hospital);

		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/hospitals")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content()
				.json("[{\"hospitalId\":10001,\"hospitalName\":\"R.G Kar Medical College and Hospital\",\"hospitalType\":\"GOV\",\"availableBed\":5000,\"isBloodBank\":true}]"))
				.andReturn();
	}
	
	@Test
	public void retrieveHospitalByIdUnitTest() throws Exception {
		
		HospitalPojo hospitalPojo = new HospitalPojo(10001L, "R.G Kar Medical College and Hospital", "GOV", 5000, true, "B3A7K6Z9R2", null, null);
		
		MappingJacksonValue hospital = MappingJacksonValueBuilder.init(hospitalPojo).addFilter(HospitalPojo.FILTER, "employees", "patients").build();
		
		when(hospitalService.retrieveHospitalById(100L))
			.thenReturn(hospital);

		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/hospitals/100")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request).andExpect(status().isOk())
				.andExpect(content()
				.json("{\"hospitalId\":100,\"hospitalName\":\"R.G Kar Medical College and Hospital\",\"hospitalType\":\"GOV\",\"availableBed\":5000,\"isBloodBank\":true}"))
				.andReturn();
	}
	
	@Test
	public void addNewHospitalUnitTest_true_scenario() throws Exception {
		
		when(hospitalService.addNewHospital(any(HospitalPojo.class)))
			.thenReturn(ResponseEntity.created(null).build());

		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("hospitalName", "XYZ Hospital");
		jsonObject.put("hospitalType", "PVT");
		jsonObject.put("availableBed", 500);
		jsonObject.put("isBloodBank", true);
		
		System.out.println(jsonObject.toString());
		
		RequestBuilder request = MockMvcRequestBuilders
				.post("/hospitals")
				.content(jsonObject.toString())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isCreated())
				.andReturn();
	}
	
	@Test
	public void addNewHospitalUnitTest_null_scenario() throws Exception {
		
		when(hospitalService.addNewHospital(any(HospitalPojo.class)))
			.thenReturn(ResponseEntity.created(null).build());

		
		JSONObject jsonObject = new JSONObject();
		
		RequestBuilder request = MockMvcRequestBuilders
				.post("/hospitals")
				.content(jsonObject.toString())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isBadRequest())
				.andReturn();
		
		System.out.println(result.getResponse().getStatus());
	}
	
	@Test
	public void addNewHospitalUnitTest_false_scenario() throws Exception {
		
		when(hospitalService.addNewHospital(any(HospitalPojo.class)))
			.thenReturn(ResponseEntity.created(null).build());

		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("hospitalName", "X");
		jsonObject.put("hospitalType", "P");
		jsonObject.put("availableBed", 500);
		jsonObject.put("isBloodBank", true);
		
		RequestBuilder request = MockMvcRequestBuilders
				.post("/hospitals")
				.content(jsonObject.toString())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isBadRequest())
				.andReturn();
		
		System.out.println(result.getResponse().getStatus());
	}
	
	@Test
	public void enableBloodBankUnitTest() throws Exception {
		
		when(hospitalService.enableBloodBank(1L))
			.thenReturn(new HospitalPojo(1L, "XYZ Hospital", "PVT", 500, false, "B3A7K6Z9R2",null, null));

		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/hospitals/1/services/enable/bloodbank")
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(request)
				.andExpect(status().isCreated())
				.andReturn();
	}
}
