package com.cognizant.app.patientmanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cognizant.app.patientmanagement.dto.EmployeeChangePasswordDto;
import com.cognizant.app.patientmanagement.dto.EmployeeDetailsUpdationDto;
import com.cognizant.app.patientmanagement.dto.EmployeeLoginDto;
import com.cognizant.app.patientmanagement.pojo.EmployeePojo;

import jakarta.validation.Valid;

public interface EmployeeController {

	@PostMapping("/authenticate")
	public MappingJacksonValue authenticate(@RequestBody EmployeeLoginDto employeeLoginDto);

	@PostMapping(path = "/employees")
	public ResponseEntity<Void> addNewEmployee(@RequestBody @Valid EmployeePojo employee);

	@GetMapping(path = "/hospitals/{hospitalId}/employees")
	public MappingJacksonValue retrieveEmployeesOfSpecificHospital(@PathVariable Long hospitalId);

	@GetMapping(path = "/hospitals/{hospitalId}/employees/{employeeId}")
	public MappingJacksonValue retrieveSpecificEmployeeOfSpecificHospital(@PathVariable Long hospitalId,
			@PathVariable Long employeeId);

	@PutMapping(path = "/hospitals/{hospitalId}/employees/{employeeId}")
	public MappingJacksonValue updateDetailsOfSpecificEmployeeOfSpecificHospital(@PathVariable Long hospitalId,
			@PathVariable Long employeeId, @RequestBody @Valid EmployeeDetailsUpdationDto employeeDetailsUpdationDto);
	
	@PutMapping(path = "/hospitals/{hospitalId}/employees/{employeeId}/change-password")
	public ResponseEntity<MappingJacksonValue> changePasswordOfSpecificUserOfSpecificHospital(@PathVariable Long hospitalId,
			@PathVariable Long employeeId, @RequestBody @Valid EmployeeChangePasswordDto employeeChangePasswordDto);

}
