package com.cognizant.app.patientmanagement.service;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;

import com.cognizant.app.patientmanagement.dto.EmployeeChangePasswordDto;
import com.cognizant.app.patientmanagement.dto.EmployeeDetailsUpdationDto;
import com.cognizant.app.patientmanagement.dto.EmployeeLoginDto;
import com.cognizant.app.patientmanagement.pojo.EmployeePojo;

public interface EmployeeService {

	public MappingJacksonValue authenticate(EmployeeLoginDto employeeLoginDto);

	public ResponseEntity<Void> addNewEmployee(EmployeePojo employee);

	public MappingJacksonValue retrieveEmployeesOfSpecificHospital(Long hospitalId);

	public MappingJacksonValue retrieveSpecificEmployeeOfSpecificHospital(Long hospitalId, Long employeeId);

	public MappingJacksonValue updateDetailsOfSpecificEmployeeOfSpecificHospital(Long hospitalId, Long employeeId,
			EmployeeDetailsUpdationDto employeeDetailsUpdationDto);

	public MappingJacksonValue changePasswordOfSpecificUserOfSpecificHospital(Long hospitalId, Long employeeId,
			EmployeeChangePasswordDto employeeChangePasswordDto);

}
