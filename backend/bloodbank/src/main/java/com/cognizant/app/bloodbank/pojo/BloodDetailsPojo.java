package com.cognizant.app.bloodbank.pojo;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "blood_details")
public class BloodDetailsPojo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bloodId;
	private String bloodGroup;
	private String donorName;
	private String donorSex;
	private int donorAge;
	private LocalDate donationDate;
	private int donatedUnits;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "hospital_id")
	private BloodBankPojo bloodBankPojo;

}
