package com.cognizant.app.bloodbank.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cognizant.app.bloodbank.pojo.BloodDetailsPojo;

public interface BloodDetailsController {

	@GetMapping("/bloodbank/{hospitalId}/blooddetails")
	public List<BloodDetailsPojo> retrieveAllBloodDetailsOfSpecificHospital(
			@PathVariable("hospitalId") Long hospitalId);

	@GetMapping("/bloodbank/{hospitalId}/blooddetails/{bloodId}")
	public BloodDetailsPojo retrieveSpecificBloodDetailsOfSpecificHospital(@PathVariable("hospitalId") Long hospitalId,
			@PathVariable("bloodId") Long bloodId);

	@PostMapping("/bloodbank/{hospitalId}/blooddetails")
	public ResponseEntity<Void> addNewBloodDetailsOfSpecificHospital(@PathVariable("hospitalId") Long hospitalId,
			@RequestBody BloodDetailsPojo bloodDetailsPojo);

	@DeleteMapping("/bloodbank/{hospitalId}/blooddetails/{bloodId}")
	public ResponseEntity<Void> deleteBloodDetailsByIdOfSpecificHospital(@PathVariable("hospitalId") Long hospitalId,
			@PathVariable("bloodId") Long bloodId);

	@PutMapping("/bloodbank/{hospitalId}/blooddetails/{bloodId}")
	public ResponseEntity<Void> updateBloodDetailsByIdOfSpecificHospital(@PathVariable("hospitalId") Long hospitalId,
			@PathVariable("bloodId") Long bloodId, @RequestBody BloodDetailsPojo bloodDetailsPojo);

}
