package com.cognizant.app.patientmanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
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

import com.cognizant.app.patientmanagement.controller.impl.PatientControllerImpl;
import com.cognizant.app.patientmanagement.exception.ResourceNotFoundException;
import com.cognizant.app.patientmanagement.pojo.HospitalPojo;
import com.cognizant.app.patientmanagement.pojo.PatientPojo;
import com.cognizant.app.patientmanagement.repository.HospitalJpaRepository;
import com.cognizant.app.patientmanagement.repository.PatientJpaRepository;
import com.cognizant.app.patientmanagement.service.impl.PatientServiceImpl;
import com.cognizant.app.patientmanagement.utility.MappingJacksonValueBuilder;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(PatientServiceImpl.class)
public class PatientServiceImplUnitTest {
	@MockBean
	private PatientJpaRepository patientJpaRepository;

	@MockBean
	private HospitalJpaRepository hospitalJpaRepository;

	@MockBean
	private PatientControllerImpl patientControllerImpl;

	@InjectMocks
	private PatientServiceImpl patientServiceImpl;

	@Test
	public void findAllPatientsOfSpecificHospitalUnitTest() throws Exception {
		HospitalPojo hospitalPojo = new HospitalPojo(1L, "Sample Hospital", "GOV", 5000, true, "B3A7K6Z9R2", null, null);
		PatientPojo patientPojo = new PatientPojo(10, "Sample Patient", "M", 33, "ICU", "Sample disease",
				LocalDate.now(), "Sample Doctor", LocalTime.now(), LocalDate.now(), hospitalPojo);

		List<PatientPojo> patients = Arrays.asList(patientPojo);

		hospitalPojo.setPatients(patients);

		when(hospitalJpaRepository.findById(anyLong())).thenReturn(Optional.of(hospitalPojo));

		MappingJacksonValue actualPatients = patientServiceImpl.retrieveAllPatientsOfSpecificHospital(1L);

		assertEquals(
				MappingJacksonValueBuilder.init(patients).addFilter(PatientPojo.FILTER)
						.addFilter(HospitalPojo.FILTER, "employees", "patients").build().getValue(),
				actualPatients.getValue());

	}

	@Test
	public void findPatientByIdOfSpecifiHospitalUnitTest() throws Exception {
		HospitalPojo hospitalPojo = new HospitalPojo(1L, "Sample Hospital", "GOV", 5000, true, "B3A7K6Z9R2", null, null);
		PatientPojo patientPojo = new PatientPojo(10, "Sample Patient", "M", 33, "ICU", "Sample disease",
				LocalDate.now(), "Sample Doctor", LocalTime.now(), LocalDate.now(), hospitalPojo);
		List<PatientPojo> patients = Arrays.asList(patientPojo);

		hospitalPojo.setPatients(patients);

		when(hospitalJpaRepository.findById(anyLong())).thenReturn(Optional.of(hospitalPojo));
		when(patientJpaRepository.findById(anyInt())).thenReturn(Optional.of(patientPojo));

		MappingJacksonValue actualPatient = patientServiceImpl.retrieveSpecificPatientOfSpecificHospital(1L, 10);

		assertEquals(
				MappingJacksonValueBuilder.init(patientPojo).addFilter(PatientPojo.FILTER)
						.addFilter(HospitalPojo.FILTER, "employees", "patients").build().getValue(),
				actualPatient.getValue());
	}
	
	@Test 
	public void findPatientByIdOfSpecifiHospitalUnitTest_throwsException_invalidId() throws ResourceNotFoundException {
		HospitalPojo hospitalPojo = new HospitalPojo(1L, "Sample Hospital", "GOV", 5000, true, "B3A7K6Z9R2", null, null);
		PatientPojo patientPojo = new PatientPojo(10, "Sample Patient", "M", 33, "ICU", "Sample disease",
				LocalDate.now(), "Sample Doctor", LocalTime.now(), LocalDate.now(), hospitalPojo);
		hospitalPojo.setPatients(Arrays.asList(patientPojo));
		Integer patientId = 1;
		
		when(hospitalJpaRepository.findById(anyLong())).thenReturn(Optional.of(hospitalPojo));
		when(patientJpaRepository.findById(patientId)).thenReturn(Optional.empty());
		
		assertThrows(ResourceNotFoundException.class, () -> patientServiceImpl.retrieveSpecificPatientOfSpecificHospital(1L, patientId));	
	}

	@Test
	public void addNewPatientUnitTest() throws Exception {
		HospitalPojo hospitalPojo = new HospitalPojo(1L, "Sample Hospital", "GOV", 5000, true, "B3A7K6Z9R2", null, null);

		PatientPojo patientPojo = new PatientPojo(null, "Sample Patient", "M", 33, "ICU", "Sample disease",
				LocalDate.now(), "Sample Doctor", LocalTime.now(), LocalDate.now(), null);

		when(hospitalJpaRepository.findById(anyLong())).thenReturn(Optional.of(hospitalPojo));
		when(patientJpaRepository.save(any(PatientPojo.class))).thenReturn(patientPojo);

		ResponseEntity<Void> actualResponse = patientServiceImpl.addNewPatient(patientPojo, 1L);

		assertEquals(201, actualResponse.getStatusCode().value());

		verify(patientJpaRepository, times(1)).save(patientPojo);

	}

	@Test
	public void deletePatientByIdUnitTest() throws Exception {
		HospitalPojo hospitalPojo = new HospitalPojo(1L, "Sample Hospital", "GOV", 5000, true, "B3A7K6Z9R2", null, null);

		PatientPojo patientPojo = new PatientPojo(10, "Sample Patient", "M", 33, "ICU", "Sample disease",
				LocalDate.now(), "Sample Doctor", LocalTime.now(), LocalDate.now(), hospitalPojo);
		hospitalPojo.setPatients(Arrays.asList(patientPojo));

		when(hospitalJpaRepository.findById(anyLong())).thenReturn(Optional.of(hospitalPojo));
		when(patientJpaRepository.save(any(PatientPojo.class))).thenReturn(patientPojo);
		doNothing().when(patientJpaRepository).delete(patientPojo);

		ResponseEntity<Void> actuaResponse = patientServiceImpl.deletePatientById(1L, 10);

		assertEquals(204, actuaResponse.getStatusCode().value());
		verify(patientJpaRepository, times(1)).delete(patientPojo);
	}

	@Test
	public void updatePatientByIdUnitTest() throws Exception {
		HospitalPojo hospitalPojo = new HospitalPojo(1L, "Sample Hospital", "GOV", 5000, true, "B3A7K6Z9R2", null, null);

		PatientPojo patientPojo = new PatientPojo(10, "Sample Patient", "M", 33, "ICU", "Sample disease",
				LocalDate.now(), "Sample Doctor", LocalTime.now(), LocalDate.now(), hospitalPojo);
		hospitalPojo.setPatients(Arrays.asList(patientPojo));

		PatientPojo updatedPatientPojo = new PatientPojo(10, "Sample Patient updated", "F", 33, "ICU-updated",
				"Sample disease updated", LocalDate.now(), "Sample Doctor updated", LocalTime.now(), LocalDate.now(),
				hospitalPojo);

		when(hospitalJpaRepository.findById(anyLong())).thenReturn(Optional.of(hospitalPojo));
		when(patientJpaRepository.findById(anyInt())).thenReturn(Optional.of(updatedPatientPojo));
		when(patientJpaRepository.save(any(PatientPojo.class))).thenReturn(updatedPatientPojo);

		ResponseEntity<Void> actualResponse = patientServiceImpl.updatePatient(updatedPatientPojo, 1L, 10);

		assertEquals(201, actualResponse.getStatusCode().value());
	}
	
}
