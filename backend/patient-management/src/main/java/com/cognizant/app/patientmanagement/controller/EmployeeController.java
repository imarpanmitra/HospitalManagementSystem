package com.cognizant.app.patientmanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cognizant.app.patientmanagement.pojo.EmployeePojo;

public interface EmployeeController {

	@PostMapping("/authenticate")
	public MappingJacksonValue authenticate(@RequestBody EmployeePojo employee);

	@PostMapping(path = "/employees")
	public ResponseEntity<Void> addNewEmployee(@RequestBody EmployeePojo employee);

	@GetMapping(path = "/hospitals/{hospitalId}/employees")
	public MappingJacksonValue retrieveEmployeesOfSpecificHospital(@PathVariable Long hospitalId);

	@GetMapping(path = "/hospitals/{hospitalId}/employees/{employeeId}")
	public MappingJacksonValue retrieveSpecificEmployeeOfSpecificHospital(@PathVariable Long hospitalId,
			@PathVariable Long employeeId);
	
}
