package com.cognizant.app.patientmanagement.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;

import com.cognizant.app.patientmanagement.pojo.EmployeePojo;

public interface EmployeeService {

	public MappingJacksonValue authenticate(EmployeePojo employee);

	public ResponseEntity<Void> addNewEmployee(EmployeePojo employee);

	public  MappingJacksonValue retrieveEmployeesOfSpecificHospital(Long hospitalId);

	public MappingJacksonValue retrieveSpecificEmployeeOfSpecificHospital(Long hospitalId, Long employeeId);

}
