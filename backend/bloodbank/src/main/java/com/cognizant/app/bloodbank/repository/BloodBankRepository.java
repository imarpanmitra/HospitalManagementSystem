package com.cognizant.app.bloodbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.app.bloodbank.pojo.BloodBankPojo;

public interface BloodBankRepository extends JpaRepository<BloodBankPojo, Long> {
	public BloodBankPojo findByHospitalId(Long hospitalId);
}
