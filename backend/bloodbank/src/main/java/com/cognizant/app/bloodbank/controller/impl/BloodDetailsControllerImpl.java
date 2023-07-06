package com.cognizant.app.bloodbank.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.app.bloodbank.controller.BloodDetailsController;
import com.cognizant.app.bloodbank.pojo.BloodDetailsPojo;
import com.cognizant.app.bloodbank.service.BloodBankDetailsService;

@RestController
public class BloodDetailsControllerImpl implements BloodDetailsController {
	@Autowired
	private BloodBankDetailsService bloodBankDetailsService;

	@Override
	public List<BloodDetailsPojo> retrieveAllBloodDetailsOfSpecificHospital(Long hospitalId) {
		return bloodBankDetailsService.retrieveAllBloodDetailsOfSpecificHospital(hospitalId);
	}

	@Override
	public BloodDetailsPojo retrieveSpecificBloodDetailsOfSpecificHospital(Long hospitalId, Long bloodId) {
		return bloodBankDetailsService.retrieveSpecificBloodDetailsOfSpecificHospital(hospitalId, bloodId);
	}

	@Override
	public ResponseEntity<Void> addNewBloodDetailsOfSpecificHospital(Long hospitalId,
			BloodDetailsPojo bloodDetailsPojo) {
		bloodBankDetailsService.addNewBloodDetailsOfSpecificHospital(hospitalId, bloodDetailsPojo);
		return ResponseEntity.created(null).build();
	}

	@Override
	public ResponseEntity<Void> deleteBloodDetailsByIdOfSpecificHospital(Long hospitalId, Long bloodId) {
		bloodBankDetailsService.deleteBloodDetailsByIdOfSpecificHospital(hospitalId, bloodId);
		return ResponseEntity.noContent().build();
	}
	
	@Override
	public ResponseEntity<Void> updateBloodDetailsByIdOfSpecificHospital(Long hospitalId, Long bloodId,
			BloodDetailsPojo bloodDetailsPojo) {
		bloodBankDetailsService.updateBloodDetailsByIdOfSpecificHospital(hospitalId, bloodId, bloodDetailsPojo);
		return ResponseEntity.created(null).build();
	}

}
