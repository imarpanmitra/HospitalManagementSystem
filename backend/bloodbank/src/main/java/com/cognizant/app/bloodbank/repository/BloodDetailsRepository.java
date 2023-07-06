package com.cognizant.app.bloodbank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.app.bloodbank.pojo.BloodDetailsPojo;


public interface BloodDetailsRepository extends JpaRepository<BloodDetailsPojo, Long> {
	
}
