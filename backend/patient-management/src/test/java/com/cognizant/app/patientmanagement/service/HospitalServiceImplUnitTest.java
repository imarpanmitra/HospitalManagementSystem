package com.cognizant.app.patientmanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;

import com.cognizant.app.patientmanagement.controller.impl.HospitalControllerImpl;
import com.cognizant.app.patientmanagement.exception.ResourceNotFoundException;
import com.cognizant.app.patientmanagement.pojo.HospitalPojo;
import com.cognizant.app.patientmanagement.repository.HospitalJpaRepository;
import com.cognizant.app.patientmanagement.service.impl.HospitalServiceImpl;
import com.cognizant.app.patientmanagement.utility.MappingJacksonValueBuilder;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(HospitalServiceImpl.class)
public class HospitalServiceImplUnitTest {

	@MockBean
	private HospitalJpaRepository hospitalJpaRepository;

	@MockBean
	private HospitalControllerImpl hospitalControllerImpl;

	@InjectMocks
	private HospitalServiceImpl hospitalServiceImpl;

	@Test
	public void retrieveAllHospitalsUnitTest() throws Exception {
		HospitalPojo hospitalPojo = new HospitalPojo(10001L, "R.G Kar Medical College and Hospital", "GOV", 5000, true,
			"B3A7K6Z9R2",null, null);

		List<HospitalPojo> hospitals = new ArrayList<>();

		hospitals.add(hospitalPojo);

		when(hospitalJpaRepository.findAll()).thenReturn(hospitals);

		MappingJacksonValue actualHospitals = hospitalServiceImpl.retrieveAllHospitals();

		assertEquals(MappingJacksonValueBuilder.init(hospitals).addFilter(HospitalPojo.FILTER, "employees", "patients")
				.build().getValue(), actualHospitals.getValue());

	}

	@Test
	public void retrieveHospitalByIdUnitTest() throws Exception {
		HospitalPojo hospitalPojo = new HospitalPojo(1L, "Sample Hospital", "GOV", 5000, true, "B3A7K6Z9R2", null, null);

		when(hospitalJpaRepository.findById(anyLong())).thenReturn(Optional.of(hospitalPojo));

		MappingJacksonValue actualHospital = hospitalServiceImpl.retrieveHospitalById(1L);

		assertEquals(MappingJacksonValueBuilder.init(hospitalPojo)
				.addFilter(HospitalPojo.FILTER, "employees", "patients").build().getValue(), actualHospital.getValue());

	}
	
	@Test
	public void retrieveHospitalByIdUnitTest_throwsException_uponInvalidId() throws ResourceNotFoundException {
		Long hospitalId = 7L;

		when(hospitalJpaRepository.findById(anyLong())).thenReturn(Optional.empty());

		assertThrows(ResourceNotFoundException.class, () -> hospitalServiceImpl.retrieveHospitalById(hospitalId));	
	}
	
	@Test
	public void addNewHospitalUnitTest() throws Exception {
		HospitalPojo hospitalPojo = new HospitalPojo(null, "Sample Hospital", "GOV", 5000, true, "B3A7K6Z9R2", null, null);
		
		when(hospitalJpaRepository.findByHospitalName(anyString())).thenReturn(null);
		
		when(hospitalJpaRepository.save(any(HospitalPojo.class))).thenReturn(hospitalPojo);
		
		ResponseEntity<Void> responseEntity = hospitalServiceImpl.addNewHospital(hospitalPojo);
		
		assertEquals(201, responseEntity.getStatusCode().value());
		
		verify(hospitalJpaRepository, times(1)).save(hospitalPojo);
		
	}
	
	@Test
	public void enableBloodBankUnitTest() throws Exception {
		HospitalPojo hospitalPojo = new HospitalPojo(1L, "Sample Hospital", "GOV", 5000, false, "B3A7K6Z9R2", null, null);
		
		when(hospitalJpaRepository.findById(anyLong())).thenReturn(Optional.of(hospitalPojo));
		
		HospitalPojo newHospital = hospitalServiceImpl.enableBloodBank(1L);
		
		assertEquals(true, newHospital.getIsBloodBank());
	}
}
