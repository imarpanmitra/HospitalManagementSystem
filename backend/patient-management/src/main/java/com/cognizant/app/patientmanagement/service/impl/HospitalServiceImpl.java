package com.cognizant.app.patientmanagement.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;

import com.cognizant.app.patientmanagement.exception.ResourceAlreadyExistsException;
import com.cognizant.app.patientmanagement.exception.ResourceNotFoundException;
import com.cognizant.app.patientmanagement.pojo.HospitalPojo;
import com.cognizant.app.patientmanagement.repository.HospitalJpaRepository;
import com.cognizant.app.patientmanagement.service.HospitalService;
import com.cognizant.app.patientmanagement.utility.MappingJacksonValueBuilder;

import jakarta.validation.Valid;

@Service
public class HospitalServiceImpl implements HospitalService {
	@Autowired
	private HospitalJpaRepository hospitalJpaRepository;
	
	@Override
	public MappingJacksonValue retrieveAllHospitals() {
		List<HospitalPojo> hospitals = hospitalJpaRepository.findAll();
		return MappingJacksonValueBuilder.init(hospitals).addFilter(HospitalPojo.FILTER, "employees", "patients")
				.build();
	}

	@Override
	public MappingJacksonValue retrieveHospitalById(Long id) throws ResourceNotFoundException {
		Optional<HospitalPojo> optional = hospitalJpaRepository.findById(id);
		if (optional.isEmpty())
			throw new ResourceNotFoundException("No hospital found of id " + id);
		HospitalPojo hospital = optional.get();
		
		return MappingJacksonValueBuilder.init(hospital).addFilter(HospitalPojo.FILTER, "employees", "patients")
				.build();
	}

	@Override
	public ResponseEntity<Void> addNewHospital(@Valid HospitalPojo hospital) {
		HospitalPojo optionalHospital = hospitalJpaRepository.findByHospitalName(hospital.getHospitalName());
		if (optionalHospital == null) {
			hospitalJpaRepository.save(hospital);
			
			return ResponseEntity.created(null).build();
		}
		throw new ResourceAlreadyExistsException("Resource already exists");
	}


	@Override
	public HospitalPojo enableBloodBank(Long hospitalId) {
		Optional<HospitalPojo> optionalHospital = hospitalJpaRepository.findById(hospitalId);
		HospitalPojo hospital = optionalHospital.get();
		if(hospital.getIsBloodBank())
			throw new ResourceAlreadyExistsException("Resource Already Exists");
		hospital.setIsBloodBank(true);
		hospitalJpaRepository.save(hospital);
		return hospital;
	}
}
