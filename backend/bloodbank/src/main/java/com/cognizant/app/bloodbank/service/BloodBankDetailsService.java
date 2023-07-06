package com.cognizant.app.bloodbank.service;

import java.util.List;

import com.cognizant.app.bloodbank.pojo.BloodDetailsPojo;

public interface BloodBankDetailsService {

	public List<BloodDetailsPojo> retrieveAllBloodDetailsOfSpecificHospital(Long hospitalId);

	public BloodDetailsPojo retrieveSpecificBloodDetailsOfSpecificHospital(Long hospitalId, Long bloodId);

	public void addNewBloodDetailsOfSpecificHospital(Long hospitalId, BloodDetailsPojo bloodDetailsPojo);

	public void deleteBloodDetailsByIdOfSpecificHospital(Long hospitalId, Long bloodId);

	public void updateBloodDetailsByIdOfSpecificHospital(Long hospitalId, Long bloodId,
			BloodDetailsPojo bloodDetailsPojo);
}
