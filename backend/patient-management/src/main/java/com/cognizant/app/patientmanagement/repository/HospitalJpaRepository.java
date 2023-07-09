package com.cognizant.app.patientmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.app.patientmanagement.pojo.HospitalPojo;

@Repository
public interface HospitalJpaRepository extends JpaRepository<HospitalPojo, Long> {
	public HospitalPojo findByHospitalName(String hospitalName);
}
