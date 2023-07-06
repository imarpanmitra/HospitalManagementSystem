package com.cognizant.app.bloodbank.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cognizant.app.bloodbank.pojo.BloodBankPojo;

public interface BloodBankController {

	@GetMapping("/bloodbank/{hospitalId}")
	public BloodBankPojo retrieveBloodbankOfSpecificHospital(@PathVariable("hospitalId") Long hospitalId);
	
	@PostMapping("/bloodbank/{hospitalId}/enable/bloodbank")
	public ResponseEntity<Void> addNewBloodBank(@PathVariable("hospitalId") Long hospitalId);

}
