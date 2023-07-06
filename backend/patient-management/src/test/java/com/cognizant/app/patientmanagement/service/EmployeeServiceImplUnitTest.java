package com.cognizant.app.patientmanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

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

import com.cognizant.app.patientmanagement.controller.impl.EmployeeControllerImpl;
import com.cognizant.app.patientmanagement.pojo.EmployeePojo;
import com.cognizant.app.patientmanagement.pojo.HospitalPojo;
import com.cognizant.app.patientmanagement.repository.EmployeeJpaRepository;
import com.cognizant.app.patientmanagement.repository.HospitalJpaRepository;
import com.cognizant.app.patientmanagement.service.impl.EmployeeServiceImpl;
import com.cognizant.app.patientmanagement.utility.MappingJacksonValueBuilder;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(EmployeeServiceImpl.class)
public class EmployeeServiceImplUnitTest {

	@MockBean
	private EmployeeJpaRepository employeeJpaRepository;

	@MockBean
	private HospitalJpaRepository hospitalJpaRepository;

	@MockBean
	private EmployeeControllerImpl employeeControllerImpl;

	@InjectMocks
	private EmployeeServiceImpl employeeServiceImpl;

	@Test
	public void authenticateUnitTest() throws Exception {
		HospitalPojo hospital = new HospitalPojo(1L, "Sample Hospital", "GOV", 5000, true, "B3A7K6Z9R2", null, null);
		EmployeePojo employee = new EmployeePojo(10L, "Sample", "User", "M", "sample123", "sample", null, null,
				hospital);

		hospital.setEmployees(Arrays.asList(employee));

		when(employeeJpaRepository.findByUsernameAndPassword(anyString(), anyString())).thenReturn(employee);
		when(employeeJpaRepository.findByUsername(anyString())).thenReturn(employee);

		MappingJacksonValue loggedinEmployee = employeeServiceImpl.authenticate(employee);

		assertEquals(
				MappingJacksonValueBuilder.init(employee).addFilter(EmployeePojo.FILTER)
						.addFilter(HospitalPojo.FILTER, "employees", "patients").build().getValue(),
				loggedinEmployee.getValue());
	}

	@Test
	public void retrieveAllEmployeesOfSpecificHospitalUnitTest() throws Exception {
		HospitalPojo hospital = new HospitalPojo(1L, "Sample Hospital", "GOV", 5000, true, "B3A7K6Z9R2", null, null);
		EmployeePojo employee1 = new EmployeePojo(10L, "Sample", "User", "M", "sample123", "sample", null, null,
				hospital);
		EmployeePojo employee2 = new EmployeePojo(11L, "Sample2", "User2", "M", "secondSample123", "sample", null, null,
				hospital);

		List<EmployeePojo> employeesAsList = Arrays.asList(employee1, employee2);
		hospital.setEmployees(employeesAsList);

		when(hospitalJpaRepository.findById(anyLong())).thenReturn(Optional.of(hospital));

		MappingJacksonValue actualEmployees = employeeServiceImpl.retrieveEmployeesOfSpecificHospital(1L);

		assertEquals(
				MappingJacksonValueBuilder.init(employeesAsList).addFilter(EmployeePojo.FILTER)
						.addFilter(HospitalPojo.FILTER, "patients", "employees").build().getValue(),
				actualEmployees.getValue());
	}

	@Test
	public void retrieveSpecificEmployeeOfSpecificHospitalUnitTest() throws Exception {
		HospitalPojo hospital = new HospitalPojo(1L, "Sample Hospital", "GOV", 5000, true, "B3A7K6Z9R2", null, null);
		EmployeePojo employee = new EmployeePojo(10L, "Sample", "User", "M", "sample123", "sample", null, null,
				hospital);

		hospital.setEmployees(Arrays.asList(employee));

		when(hospitalJpaRepository.findById(anyLong())).thenReturn(Optional.of(hospital));
		when(employeeJpaRepository.findById(anyLong())).thenReturn(Optional.of(employee));

		MappingJacksonValue actualEmployee = employeeServiceImpl.retrieveSpecificEmployeeOfSpecificHospital(1L, 10L);

		assertEquals(
				MappingJacksonValueBuilder.init(employee).addFilter(EmployeePojo.FILTER)
						.addFilter(HospitalPojo.FILTER, "employees", "patients").build().getValue(),
				actualEmployee.getValue());
	}

	@Test
	public void addNewEmployeeUnitTest() throws Exception {
		HospitalPojo hospital = new HospitalPojo(1L, "Sample Hospital", "GOV", 5000, true, "B3A7K6Z9R2", null, null);

		EmployeePojo employee = new EmployeePojo(10L, "Sample", "User", "M", "sample123", "sample", "B3A7K6Z9R2",
				"Sample Hospital", null);

		when(hospitalJpaRepository.findByHospitalName(anyString())).thenReturn(hospital);
		when(employeeJpaRepository.findByUsername(anyString())).thenReturn(null);
		when(employeeJpaRepository.save(employee)).thenReturn(employee);

		ResponseEntity<Void> actualResponse = employeeServiceImpl.addNewEmployee(employee);

		assertEquals(201, actualResponse.getStatusCode().value());
	}
}
