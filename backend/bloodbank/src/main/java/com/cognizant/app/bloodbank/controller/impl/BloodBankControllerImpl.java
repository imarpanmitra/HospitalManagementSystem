package com.cognizant.app.bloodbank.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.app.bloodbank.controller.BloodBankController;
import com.cognizant.app.bloodbank.pojo.BloodBankPojo;
import com.cognizant.app.bloodbank.service.BloodBankService;

@RestController
public class BloodBankControllerImpl implements BloodBankController{

	@Autowired
	private BloodBankService bloodBankService;
	
	@Override
	public BloodBankPojo retrieveBloodbankOfSpecificHospital(Long hospitalId) {
		return bloodBankService.retrieveBloodbankOfSpecificHospital(hospitalId);
	}

	@Override
	public ResponseEntity<Void> addNewBloodBank(Long hospitalId) {
		bloodBankService.addNewBloodBank(hospitalId);
		return ResponseEntity.created(null).build();
	}

}
