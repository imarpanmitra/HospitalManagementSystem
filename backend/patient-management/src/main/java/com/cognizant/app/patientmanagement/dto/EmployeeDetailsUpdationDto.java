package com.cognizant.app.patientmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDetailsUpdationDto {
	private String employeeFirstName;
	private String employeeLastName;
	private String employeeSex;
}
