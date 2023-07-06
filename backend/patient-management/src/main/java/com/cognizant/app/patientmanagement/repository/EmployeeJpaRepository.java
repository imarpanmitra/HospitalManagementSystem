package com.cognizant.app.patientmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.app.patientmanagement.pojo.EmployeePojo;

public interface EmployeeJpaRepository extends JpaRepository<EmployeePojo, Long> {
	public EmployeePojo findByUsernameAndPassword(String username, String password);
	public EmployeePojo findByUsername(String username);
	//public EmployeePojo findByHospitalId(Long hospitalId);
}
