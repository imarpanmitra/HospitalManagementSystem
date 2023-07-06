package com.cognizant.app.patientmanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cognizant.app.patientmanagement.pojo.HospitalPojo;

import jakarta.validation.Valid;



public interface HospitalController {
	
	@GetMapping(path = "/hospitals")
	public MappingJacksonValue retrieveAllHospitals() ;
	
	@GetMapping(path = "/hospitals/{hospitalId}")
	public MappingJacksonValue retrieveHospitalById(@PathVariable Long hospitalId);
	
	@PostMapping(path = "/hospitals")
	public ResponseEntity<Void> addNewHospital(@RequestBody @Valid HospitalPojo hospital);
	
	@GetMapping("hospitals/{hospitalId}/services/enable/bloodbank")
	public ResponseEntity<Void> enableBloodBank(@PathVariable Long hospitalId);
}
