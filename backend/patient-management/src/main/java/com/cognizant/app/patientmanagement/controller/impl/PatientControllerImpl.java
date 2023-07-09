package com.cognizant.app.patientmanagement.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.app.patientmanagement.controller.PatientController;
import com.cognizant.app.patientmanagement.exception.ResourceNotFoundException;
import com.cognizant.app.patientmanagement.pojo.PatientPojo;
import com.cognizant.app.patientmanagement.service.PatientService;

@RestController
public class PatientControllerImpl implements PatientController{
	
	@Autowired
	private PatientService patientService;
	
	@Override
	public MappingJacksonValue retrieveAllPatientsOfSpecificHospital(Long hospitalId)
			throws ResourceNotFoundException {
		return patientService.retrieveAllPatientsOfSpecificHospital(hospitalId);
	}
	
	
	@Override
	public MappingJacksonValue retrieveSpecificPatientOfSpecificHospital(Long hospitalId,
			Integer patientId) throws ResourceNotFoundException {
		return patientService.retrieveSpecificPatientOfSpecificHospital(hospitalId, patientId);
		
	}

	@Override
	public ResponseEntity<Void> addNewPatient(PatientPojo patient, Long hospitalId) {
		return patientService.addNewPatient(patient, hospitalId);
	}

	@Override
	public ResponseEntity<Void> deletePatientById(Long hospitalId, Integer patientId) {
		return patientService.deletePatientById(hospitalId, patientId);
	}


	@Override
	public ResponseEntity<Void> updatePatient(PatientPojo patient, Long hospitalId, Integer patientId) {
		return patientService.updatePatient(patient, hospitalId, patientId);
	}
}
