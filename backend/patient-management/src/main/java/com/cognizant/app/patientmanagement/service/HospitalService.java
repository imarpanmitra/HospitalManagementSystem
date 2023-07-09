package com.cognizant.app.patientmanagement.service;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;

import com.cognizant.app.patientmanagement.pojo.HospitalPojo;


public interface HospitalService {
	public MappingJacksonValue retrieveAllHospitals();

	public MappingJacksonValue retrieveHospitalById(Long id);

	public ResponseEntity<Void> addNewHospital(HospitalPojo hospital);
	
	public HospitalPojo enableBloodBank(Long hospitalId);
}
