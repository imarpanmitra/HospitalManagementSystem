package com.cognizant.app.patientmanagement.service;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;

import com.cognizant.app.patientmanagement.pojo.PatientPojo;

public interface PatientService {

	public MappingJacksonValue retrieveAllPatientsOfSpecificHospital(Long hospitalId);

	public MappingJacksonValue retrieveSpecificPatientOfSpecificHospital(Long hospitalId, Integer patientId);

	public ResponseEntity<Void> addNewPatient(PatientPojo patient, Long hospitalId);

	public ResponseEntity<Void> deletePatientById(Long hospitalId, Integer patientId);

	public ResponseEntity<Void> updatePatient(PatientPojo patient, Long hospitalId, Integer patientId);
}
