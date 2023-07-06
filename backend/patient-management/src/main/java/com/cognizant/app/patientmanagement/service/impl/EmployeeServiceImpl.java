package com.cognizant.app.patientmanagement.service.impl;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import com.cognizant.app.patientmanagement.exception.AuthorizationKeyNotValidException;
import com.cognizant.app.patientmanagement.exception.ResourceAlreadyExistsException;
import com.cognizant.app.patientmanagement.exception.ResourceNotFoundException;
import com.cognizant.app.patientmanagement.pojo.EmployeePojo;
import com.cognizant.app.patientmanagement.pojo.HospitalPojo;
import com.cognizant.app.patientmanagement.repository.EmployeeJpaRepository;
import com.cognizant.app.patientmanagement.repository.HospitalJpaRepository;
import com.cognizant.app.patientmanagement.service.EmployeeService;
import com.cognizant.app.patientmanagement.utility.MappingJacksonValueBuilder;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeJpaRepository employeeJpaRepository;

	@Autowired
	private HospitalJpaRepository hospitalJpaRepository;

	@Override
	public MappingJacksonValue authenticate(EmployeePojo employee) throws ResourceNotFoundException {
		if (employeeJpaRepository.findByUsernameAndPassword(employee.getUsername(), employee.getPassword()) != null) {

			EmployeePojo existingEmployee = employeeJpaRepository.findByUsername(employee.getUsername());

			return MappingJacksonValueBuilder.init(existingEmployee).addFilter(EmployeePojo.FILTER)
					.addFilter(HospitalPojo.FILTER, "employees", "patients").build();

		} else
			throw new ResourceNotFoundException("No user found");
	}

	@Override
	public ResponseEntity<Void> addNewEmployee(EmployeePojo employee) {

		String hospitalName = employee.getSelectedHospital();

		HospitalPojo hospital = hospitalJpaRepository.findByHospitalName(hospitalName);

		if (hospital != null) {
			if (employee.getHighSecurityPassword() == null)
				throw new AuthorizationKeyNotValidException("Not a valid authorization key");
			else if (employee.getHighSecurityPassword().equals(hospital.getAuthorizationKeyHospitalSpecific())
					&& employeeJpaRepository.findByUsername(employee.getUsername()) == null) {
				employee.setHospitalPojo(hospital);
				employeeJpaRepository.save(employee);

				return ResponseEntity.created(null).build();
			}
			
			else if(employee.getHighSecurityPassword().equals(hospital.getAuthorizationKeyHospitalSpecific())
					&& employeeJpaRepository.findByUsername(employee.getUsername()) != null) {
				throw new ResourceAlreadyExistsException("Username " + employee.getUsername() + " already exists");
			}

			else
				throw new AuthorizationKeyNotValidException("Not a valid authorization key");
		} else {
			throw new ResourceNotFoundException("No hospital selected");
		}
	}

//	@Override
//	public MappingJacksonValue retrieveEmployeesOfSpecificHospital(Long hospitalId) {
//		Optional<HospitalPojo> optionalHospital = hospitalJpaRepository.findById(hospitalId);
//		if (optionalHospital.isEmpty())
//			throw new ResourceNotFoundException("No Hospital found of id " + hospitalId);
//
//		HospitalPojo hospital = optionalHospital.get();
//
//		List<EmployeePojo> employees = hospital.getEmployees();
//
//		return MappingJacksonValueBuilder.init(employees).addFilter(EmployeePojo.FILTER)
//				.addFilter(HospitalPojo.FILTER, "employees", "patients").build();
//	}
	@Override
	public MappingJacksonValue retrieveEmployeesOfSpecificHospital(Long hospitalId) {
		Optional<HospitalPojo> optionalHospital = hospitalJpaRepository.findById(hospitalId);
		if (optionalHospital.isEmpty())
			throw new ResourceNotFoundException("No Hospital found of id " + hospitalId);

		HospitalPojo hospital = optionalHospital.get();

		List<EmployeePojo> employees = hospital.getEmployees();

		return MappingJacksonValueBuilder.init(employees).addFilter(EmployeePojo.FILTER)
				.addFilter(HospitalPojo.FILTER, "patients", "employees").build();
	}

	@Override
	public MappingJacksonValue retrieveSpecificEmployeeOfSpecificHospital(Long hospitalId, Long employeeId) {
		Optional<HospitalPojo> optionalHospital = hospitalJpaRepository.findById(hospitalId);
		if (optionalHospital.isEmpty())
			throw new ResourceNotFoundException("No Hospital found of id " + hospitalId);

		HospitalPojo hospital = optionalHospital.get();

		List<EmployeePojo> employees = hospital.getEmployees();
		Optional<EmployeePojo> optionalEmployee = employees.stream().filter(x -> x.getEmployeeId().equals(employeeId))
				.findFirst();
		if (optionalEmployee.isEmpty())
			throw new ResourceNotFoundException("No employee found of id: " + employeeId);

		EmployeePojo employee = optionalEmployee.get();
		return MappingJacksonValueBuilder.init(employee).addFilter(EmployeePojo.FILTER)
				.addFilter(HospitalPojo.FILTER, "employees", "patients").build();
	}

}
