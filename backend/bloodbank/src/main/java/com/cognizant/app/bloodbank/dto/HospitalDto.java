package com.cognizant.app.bloodbank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HospitalDto {
	private Long hospitalId;

	private String hospitalName;

	private String hospitalType;
	private Integer availableBed;

	private Boolean isBloodBank;
}
