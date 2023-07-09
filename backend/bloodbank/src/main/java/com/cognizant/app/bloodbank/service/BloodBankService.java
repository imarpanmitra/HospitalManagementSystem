package com.cognizant.app.bloodbank.service;

import com.cognizant.app.bloodbank.pojo.BloodBankPojo;

public interface BloodBankService {
	public BloodBankPojo retrieveBloodbankOfSpecificHospital(Long hospitalId);
	
	public BloodBankPojo addNewBloodBank(Long hospitalId);
}
