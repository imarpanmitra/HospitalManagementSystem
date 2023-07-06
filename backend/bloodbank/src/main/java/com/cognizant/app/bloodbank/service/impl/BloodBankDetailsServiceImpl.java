package com.cognizant.app.bloodbank.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cognizant.app.bloodbank.dto.HospitalDto;
import com.cognizant.app.bloodbank.exceptions.ResourceNotFoundException;
import com.cognizant.app.bloodbank.pojo.BloodBankPojo;
import com.cognizant.app.bloodbank.pojo.BloodDetailsPojo;
import com.cognizant.app.bloodbank.repository.BloodBankRepository;
import com.cognizant.app.bloodbank.repository.BloodDetailsRepository;
import com.cognizant.app.bloodbank.service.BloodBankDetailsService;

@Service
public class BloodBankDetailsServiceImpl implements BloodBankDetailsService {
	@Autowired
	private BloodDetailsRepository bloodDetailsRepository;

	@Autowired
	private BloodBankRepository bloodBankRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<BloodDetailsPojo> retrieveAllBloodDetailsOfSpecificHospital(Long hospitalId)
			throws ResourceNotFoundException {

		ResponseEntity<HospitalDto> responseEntity = restTemplate
				.getForEntity("http://localhost:8080/hospitals/" + hospitalId, HospitalDto.class);

		if (responseEntity.getStatusCode().is2xxSuccessful()) {
			if (responseEntity.getBody().getIsBloodBank() == true) {
				return bloodBankRepository.findByHospitalId(hospitalId).getBloodDetails();
			} else
				throw new ResourceNotFoundException("BloodBank Doesn't exist against the Hospital");
		} else
			throw new ResourceNotFoundException("Hospital not found for id" + hospitalId);
	}

	@Override
	public BloodDetailsPojo retrieveSpecificBloodDetailsOfSpecificHospital(Long hospitalId, Long bloodId)
			throws ResourceNotFoundException {
		List<BloodDetailsPojo> bloodDetails = retrieveAllBloodDetailsOfSpecificHospital(hospitalId);
		Optional<BloodDetailsPojo> optionalBloodDetails = bloodDetails.stream()
				.filter(x -> x.getBloodId().equals(bloodId)).findFirst();

		if(optionalBloodDetails.isEmpty())
			throw new ResourceNotFoundException("No blood details available of id "+bloodId);
		return optionalBloodDetails.get();
	}

	@Override
	public void addNewBloodDetailsOfSpecificHospital(Long hospitalId, BloodDetailsPojo bloodDetailsPojo) {
		ResponseEntity<HospitalDto> hospitalDto = restTemplate
				.getForEntity("http://localhost:8080/hospitals/" + hospitalId, HospitalDto.class);
		
		BloodBankPojo bloodBankPojo = bloodBankRepository.findByHospitalId(hospitalId);
		
		if (hospitalDto.getStatusCode().is2xxSuccessful()) {
			if(hospitalDto.getBody().getIsBloodBank() == true) {
				bloodDetailsPojo.setBloodBankPojo(bloodBankPojo);
				bloodDetailsRepository.save(bloodDetailsPojo);
			} else throw new ResourceNotFoundException("BloodBank Doesn't exist against the Hospital");
		} else throw new ResourceNotFoundException("No hospital found for id" + hospitalId);

	}
	
	@Override
	public void deleteBloodDetailsByIdOfSpecificHospital(Long hospitalId, Long bloodId) {
		BloodDetailsPojo bloodDetailsPojo = retrieveSpecificBloodDetailsOfSpecificHospital(hospitalId, bloodId);
		bloodDetailsRepository.delete(bloodDetailsPojo);
	}

	@Override
	public void updateBloodDetailsByIdOfSpecificHospital(Long hospitalId, Long bloodId,
			BloodDetailsPojo bloodDetailsPojo) {
		BloodDetailsPojo foundBloodDetailsPojo = retrieveSpecificBloodDetailsOfSpecificHospital(hospitalId, bloodId);
		foundBloodDetailsPojo.setBloodId(bloodId);
		foundBloodDetailsPojo.setDonorName(bloodDetailsPojo.getDonorName());
		foundBloodDetailsPojo.setBloodGroup(bloodDetailsPojo.getBloodGroup());
		foundBloodDetailsPojo.setDonorAge(bloodDetailsPojo.getDonorAge());
		foundBloodDetailsPojo.setDonorSex(bloodDetailsPojo.getDonorSex());
		foundBloodDetailsPojo.setDonatedUnits(bloodDetailsPojo.getDonatedUnits());
		foundBloodDetailsPojo.setDonationDate(bloodDetailsPojo.getDonationDate());
		
		bloodDetailsRepository.save(foundBloodDetailsPojo);
	}
}
