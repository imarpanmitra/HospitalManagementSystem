package com.cognizant.app.patientmanagement.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.app.patientmanagement.controller.EmployeeController;
import com.cognizant.app.patientmanagement.pojo.EmployeePojo;
import com.cognizant.app.patientmanagement.service.EmployeeService;

@RestController
public class EmployeeControllerImpl implements EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@Override
	public MappingJacksonValue authenticate(EmployeePojo employee) {
		return employeeService.authenticate(employee);
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

	
}
