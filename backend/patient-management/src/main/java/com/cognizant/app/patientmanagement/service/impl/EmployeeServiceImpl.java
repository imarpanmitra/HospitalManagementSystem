package com.cognizant.app.patientmanagement.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import com.cognizant.app.patientmanagement.dto.EmployeeChangePasswordDto;
import com.cognizant.app.patientmanagement.dto.EmployeeDetailsUpdationDto;
import com.cognizant.app.patientmanagement.dto.EmployeeLoginDto;
import com.cognizant.app.patientmanagement.exception.AuthorizationKeyNotValidException;
import com.cognizant.app.patientmanagement.exception.InvalidCredentialsException;
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
	public MappingJacksonValue authenticate(EmployeeLoginDto employeeLoginDto) throws ResourceNotFoundException {
		if (employeeJpaRepository.findByUsernameAndPassword(employeeLoginDto.getUsername(),
				employeeLoginDto.getPassword()) != null) {

			EmployeePojo existingEmployee = employeeJpaRepository.findByUsername(employeeLoginDto.getUsername());

			return MappingJacksonValueBuilder.init(existingEmployee).addFilter(EmployeePojo.FILTER)
					.addFilter(HospitalPojo.FILTER, "employees", "patients").build();

		} else
			throw new ResourceNotFoundException("Invalid username or password");
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
				employee.setEmployeeFirstName(employee.getEmployeeFirstName().substring(0, 1).toUpperCase()
						+ employee.getEmployeeFirstName().substring(1));
				employee.setEmployeeLastName(employee.getEmployeeLastName().substring(0, 1).toUpperCase()
						+ employee.getEmployeeLastName().substring(1));
				employeeJpaRepository.save(employee);

				return ResponseEntity.created(null).build();
			}

			else if (employee.getHighSecurityPassword().equals(hospital.getAuthorizationKeyHospitalSpecific())
					&& employeeJpaRepository.findByUsername(employee.getUsername()) != null) {
				throw new ResourceAlreadyExistsException(
						"Username " + employee.getUsername() + " already exists. Try with different username");
			}

			else
				throw new AuthorizationKeyNotValidException("Not a valid authorization key against " + hospitalName
						+ ". Remember you can only register as an employee if you are provided with an valid Authorization Key by your hospital");
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

	@Override
	public MappingJacksonValue updateDetailsOfSpecificEmployeeOfSpecificHospital(Long hospitalId, Long employeeId,
			EmployeeDetailsUpdationDto employeeDetailsUpdationDto) {
		Optional<HospitalPojo> optionalHospital = hospitalJpaRepository.findById(hospitalId);
		if (optionalHospital.isEmpty())
			throw new ResourceNotFoundException("No hospital found of id: " + employeeId);

		HospitalPojo hospital = optionalHospital.get();

		List<EmployeePojo> employees = hospital.getEmployees();
		Optional<EmployeePojo> optionalEmployee = employees.stream().filter(x -> x.getEmployeeId().equals(employeeId))
				.findFirst();
		if (optionalEmployee.isEmpty())
			throw new ResourceNotFoundException("No employee found of id: " + employeeId);

		EmployeePojo existingEmployee = optionalEmployee.get();

		existingEmployee.setEmployeeFirstName(employeeDetailsUpdationDto.getEmployeeFirstName());
		existingEmployee.setEmployeeLastName(employeeDetailsUpdationDto.getEmployeeLastName());
		existingEmployee.setEmployeeSex(employeeDetailsUpdationDto.getEmployeeSex());

		employeeJpaRepository.save(existingEmployee);

		return MappingJacksonValueBuilder.init(existingEmployee).addFilter(EmployeePojo.FILTER)
				.addFilter(HospitalPojo.FILTER, "employees", "patients").build();
	}

	@Override
	public MappingJacksonValue changePasswordOfSpecificUserOfSpecificHospital(Long hospitalId, Long employeeId,
			EmployeeChangePasswordDto employeeChangePasswordDto) {
		Optional<HospitalPojo> optionalHospital = hospitalJpaRepository.findById(hospitalId);
		if (optionalHospital.isEmpty())
			throw new ResourceNotFoundException("No hospital found of id: " + employeeId);

		HospitalPojo hospital = optionalHospital.get();

		List<EmployeePojo> employees = hospital.getEmployees();
		Optional<EmployeePojo> optionalEmployee = employees.stream().filter(x -> x.getEmployeeId().equals(employeeId))
				.findFirst();
		if (optionalEmployee.isEmpty())
			throw new ResourceNotFoundException("No employee found of id: " + employeeId);

		EmployeePojo existingEmployee = optionalEmployee.get();

		if (employeeChangePasswordDto.getOldPassword().equals(existingEmployee.getPassword())) {
			if (employeeChangePasswordDto.getNewPassword().equals(employeeChangePasswordDto.getConfirmNewPassword())
					&& !employeeChangePasswordDto.getNewPassword().equals(existingEmployee.getPassword())) {
				existingEmployee.setPassword(employeeChangePasswordDto.getNewPassword());
				employeeJpaRepository.save(existingEmployee);
			} else if (employeeChangePasswordDto.getNewPassword().equals(existingEmployee.getPassword()))
				throw new InvalidCredentialsException("Old and New passwords can't be same.");
			else
				throw new InvalidCredentialsException("New password doesn't match with confirm password");
		} else
			throw new InvalidCredentialsException("Wrong password");

		return MappingJacksonValueBuilder.init(existingEmployee).addFilter(EmployeePojo.FILTER)
				.addFilter(HospitalPojo.FILTER, "employees", "patients").build();
	}

}
