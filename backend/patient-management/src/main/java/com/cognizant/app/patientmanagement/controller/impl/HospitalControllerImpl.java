package com.cognizant.app.patientmanagement.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.app.patientmanagement.controller.HospitalController;
import com.cognizant.app.patientmanagement.pojo.HospitalPojo;
import com.cognizant.app.patientmanagement.service.HospitalService;

@RestController
public class HospitalControllerImpl implements HospitalController {
	
	@Autowired
	private HospitalService hospitalService;
	
	
	@Override
	public MappingJacksonValue retrieveAllHospitals() {
		return hospitalService.retrieveAllHospitals();
	}
	
	@Override
	public MappingJacksonValue retrieveHospitalById(Long hospitalId) {
		return hospitalService.retrieveHospitalById(hospitalId);
	}

	@Override
	public ResponseEntity<Void> addNewHospital(HospitalPojo hospital) {
		return hospitalService.addNewHospital(hospital);
	}


	@Override
	public ResponseEntity<Void> enableBloodBank(Long hospitalId) {
		hospitalService.enableBloodBank(hospitalId);
		return ResponseEntity.created(null).build();
	}
	
}
