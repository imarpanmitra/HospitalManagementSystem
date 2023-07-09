package com.cognizant.app.patientmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.app.patientmanagement.pojo.PatientPojo;

public interface PatientJpaRepository extends JpaRepository<PatientPojo, Integer>{

}
