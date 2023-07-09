package com.cognizant.app.patientmanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cognizant.app.patientmanagement.pojo.PatientPojo;

import jakarta.validation.Valid;

@Validated
public interface PatientController {
	@GetMapping(path = "/hospitals/{hospitalId}/patients")
	public MappingJacksonValue retrieveAllPatientsOfSpecificHospital(@PathVariable Long hospitalId);

	@GetMapping(path = "hospitals/{hospitalId}/patients/{patientId}")
	public MappingJacksonValue retrieveSpecificPatientOfSpecificHospital(@PathVariable Long hospitalId,
			@PathVariable Integer patientId);

	@PostMapping(path = "hospitals/{hospitalId}/patients")
	public ResponseEntity<Void> addNewPatient(@RequestBody @Valid PatientPojo patient, @PathVariable Long hospitalId);

	@DeleteMapping(path = "hospitals/{hospitalId}/patients/{patientId}")
	public ResponseEntity<Void> deletePatientById(@PathVariable Long hospitalId, @PathVariable Integer patientId);

	@PutMapping(path = "/hospitals/{hospitalId}/patients/{patientId}")
	public ResponseEntity<Void> updatePatient(@RequestBody @Valid PatientPojo patient, @PathVariable Long hospitalId,
			@PathVariable Integer patientId);
}
