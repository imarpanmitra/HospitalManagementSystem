package com.cognizant.app.patientmanagement.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.app.patientmanagement.controller.EmployeeController;
import com.cognizant.app.patientmanagement.dto.EmployeeChangePasswordDto;
import com.cognizant.app.patientmanagement.dto.EmployeeDetailsUpdationDto;
import com.cognizant.app.patientmanagement.dto.EmployeeLoginDto;
import com.cognizant.app.patientmanagement.pojo.EmployeePojo;
import com.cognizant.app.patientmanagement.service.EmployeeService;

@RestController
public class EmployeeControllerImpl implements EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@Override
	public MappingJacksonValue authenticate(EmployeeLoginDto employeeLoginDto) {
		return employeeService.authenticate(employeeLoginDto);
	}

	@Override
	public ResponseEntity<Void> addNewEmployee(EmployeePojo employee) {
		return employeeService.addNewEmployee(employee);
	}

	@Override
	public MappingJacksonValue retrieveEmployeesOfSpecificHospital(Long hospitalId) {
		return employeeService.retrieveEmployeesOfSpecificHospital(hospitalId);
	}

	@Override
	public MappingJacksonValue retrieveSpecificEmployeeOfSpecificHospital(Long hospitalId, Long employeeId) {
		return employeeService.retrieveSpecificEmployeeOfSpecificHospital(hospitalId, employeeId);
	}

	@Override
	public MappingJacksonValue updateDetailsOfSpecificEmployeeOfSpecificHospital(Long hospitalId, Long employeeId, EmployeeDetailsUpdationDto employeeDetailsUpdationDto) {
		return employeeService.updateDetailsOfSpecificEmployeeOfSpecificHospital(hospitalId, employeeId, employeeDetailsUpdationDto);
	}

	@Override
	public ResponseEntity<MappingJacksonValue> changePasswordOfSpecificUserOfSpecificHospital(Long hospitalId,
			Long employeeId, EmployeeChangePasswordDto employeeChangePasswordDto) {
		MappingJacksonValue employee = employeeService.changePasswordOfSpecificUserOfSpecificHospital(hospitalId, employeeId, employeeChangePasswordDto);
		return ResponseEntity.created(null).body(employee);
		
	}

	
}
