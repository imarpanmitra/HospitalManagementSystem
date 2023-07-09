package com.cognizant.app.bloodbank.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cognizant.app.bloodbank.dto.HospitalDto;
import com.cognizant.app.bloodbank.exceptions.ResourceAlreadyExistsException;
import com.cognizant.app.bloodbank.exceptions.ResourceNotFoundException;
import com.cognizant.app.bloodbank.pojo.BloodBankPojo;
import com.cognizant.app.bloodbank.repository.BloodBankRepository;
import com.cognizant.app.bloodbank.service.BloodBankService;

@Service
public class BloodBankServiceImpl implements BloodBankService {

	@Autowired
	private BloodBankRepository bloodBankRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public BloodBankPojo retrieveBloodbankOfSpecificHospital(Long hospitalId) {
		ResponseEntity<HospitalDto> hospital = restTemplate
				.getForEntity("http://localhost:8080/hospitals/" + hospitalId, HospitalDto.class);
		if (hospital.getStatusCode().is2xxSuccessful()) {
			if (hospital.getBody().getIsBloodBank() == true) {
				return bloodBankRepository.findByHospitalId(hospitalId);

			} else
				throw new ResourceNotFoundException("No blood bank exists for hospital id: " + hospitalId);
		} else
			throw new ResourceNotFoundException("No hospital found for id: " + hospitalId);
	}

	@Override
	public BloodBankPojo addNewBloodBank(Long hospitalId) {
		ResponseEntity<HospitalDto> hospital = restTemplate
				.getForEntity("http://localhost:8080/hospitals/" + hospitalId, HospitalDto.class);
		if (hospital.getStatusCode().is2xxSuccessful()) {
			if (hospital.getBody().getIsBloodBank() == false) {
				BloodBankPojo bloodBank = new BloodBankPojo(hospitalId,
						hospital.getBody().getHospitalName() + " Blood Bank", hospital.getBody().getHospitalType(),
						null);
				restTemplate.getForObject(
						"http://localhost:8080/hospitals/" + hospitalId + "/services/enable/bloodbank",
						HospitalDto.class);
				return bloodBankRepository.save(bloodBank);
			} else
				throw new ResourceAlreadyExistsException("Blood bank already exists for hospital id: " + hospitalId);
		} else
			throw new ResourceNotFoundException("No hospital found for id: " + hospitalId);
	}

}