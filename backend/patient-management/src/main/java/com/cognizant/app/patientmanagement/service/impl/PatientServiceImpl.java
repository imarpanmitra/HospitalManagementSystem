package com.cognizant.app.patientmanagement.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import com.cognizant.app.patientmanagement.exception.ResourceNotFoundException;
import com.cognizant.app.patientmanagement.pojo.HospitalPojo;
import com.cognizant.app.patientmanagement.pojo.PatientPojo;
import com.cognizant.app.patientmanagement.repository.HospitalJpaRepository;
import com.cognizant.app.patientmanagement.repository.PatientJpaRepository;
import com.cognizant.app.patientmanagement.service.PatientService;
import com.cognizant.app.patientmanagement.utility.MappingJacksonValueBuilder;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	private PatientJpaRepository patientJpaRepository;

	@Autowired
	private HospitalJpaRepository hospitalJpaRepository;

	@Override
	public MappingJacksonValue retrieveAllPatientsOfSpecificHospital(Long hospitalId)
			throws ResourceNotFoundException {

		Optional<HospitalPojo> optionalHospital = hospitalJpaRepository.findById(hospitalId);
		if (optionalHospital.isEmpty())
			throw new ResourceNotFoundException("No Hospital found of id " + hospitalId);

		HospitalPojo hospital = optionalHospital.get();

		List<PatientPojo> patients = hospital.getPatients();

		return MappingJacksonValueBuilder.init(patients).addFilter(PatientPojo.FILTER)
				.addFilter(HospitalPojo.FILTER, "employees", "patients").build();

	}

	@Override
	public MappingJacksonValue retrieveSpecificPatientOfSpecificHospital(Long hospitalId, Integer patientId)
			throws ResourceNotFoundException {

		Optional<HospitalPojo> optionalHospital = hospitalJpaRepository.findById(hospitalId);
		if (optionalHospital.isEmpty())
			throw new ResourceNotFoundException("No Hospital found of id " + hospitalId);

		HospitalPojo hospital = optionalHospital.get();

		List<PatientPojo> patients = hospital.getPatients();

		Optional<PatientPojo> optionalPatient = patients.stream().filter(x -> x.getPatientId().equals(patientId))
				.findFirst();

		if (optionalPatient.isEmpty())
			throw new ResourceNotFoundException("No patient found of id " + patientId);

		PatientPojo patient = optionalPatient.get();

		return MappingJacksonValueBuilder.init(patient).addFilter(PatientPojo.FILTER)
				.addFilter(HospitalPojo.FILTER, "employees", "patients").build();
	}

	@Override
	public ResponseEntity<Void> addNewPatient(PatientPojo patient, Long hospitalId) {
		Optional<HospitalPojo> optionalHospital = hospitalJpaRepository.findById(hospitalId);

		if (optionalHospital.isEmpty())
			throw new ResourceNotFoundException("No Hospital found of id " + hospitalId);

		HospitalPojo hospital = optionalHospital.get();
		patient.setHospitalPojo(hospital);
		patientJpaRepository.save(patient);
		hospital.setAvailableBed(hospital.getAvailableBed() - 1);
		hospitalJpaRepository.save(hospital);
		
		return ResponseEntity.created(null).build();
	}

	@Override
	public ResponseEntity<Void> deletePatientById(Long hospitalId, Integer patientId) {

		Optional<HospitalPojo> optionalHospital = hospitalJpaRepository.findById(hospitalId);

		if (optionalHospital.isEmpty())
			throw new ResourceNotFoundException("No Hospital found of id " + hospitalId);

		HospitalPojo hospital = optionalHospital.get();
		List<PatientPojo> patients = hospital.getPatients();
		PatientPojo patient = patients.stream().filter(x -> x.getPatientId().equals(patientId)).findFirst().get();
		hospital.setAvailableBed(hospital.getAvailableBed() + 1);
		hospitalJpaRepository.save(hospital);
		patientJpaRepository.delete(patient);

		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<Void> updatePatient(PatientPojo patient, Long hospitalId, Integer patientId) {

		Optional<HospitalPojo> optionalHospital = hospitalJpaRepository.findById(hospitalId);

		if (optionalHospital.isEmpty())
			throw new ResourceNotFoundException("No Hospital found of id " + hospitalId);

		HospitalPojo hospital = optionalHospital.get();
		List<PatientPojo> patients = hospital.getPatients();
		PatientPojo specificPatient = patients.stream().filter(x -> x.getPatientId().equals(patientId)).findFirst()
				.get();

		specificPatient.setPatientId(patientId);
		specificPatient.setPatientName(patient.getPatientName());
		specificPatient.setPatientSex(patient.getPatientSex());
		specificPatient.setPatientAge(patient.getPatientAge());
		specificPatient.setBedNo(patient.getBedNo());
		specificPatient.setDisease(patient.getDisease());
		specificPatient.setDoctorName(patient.getDoctorName());
		specificPatient.setLastVisited(patient.getLastVisited());
		specificPatient.setReleaseDate(patient.getReleaseDate());
		specificPatient.setAdmissionDate(patient.getAdmissionDate());

		patientJpaRepository.save(specificPatient);

		return ResponseEntity.created(null).build();
	}
}
