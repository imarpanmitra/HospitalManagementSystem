package com.cognizant.app.bloodbank.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "blood_bank")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BloodBankPojo {

	@Id
	private Long hospitalId;

	private String bloodBankName;
	private String bloodBankType;
	
	

	@OneToMany(mappedBy = "bloodBankPojo")
	@JsonManagedReference
	private List<BloodDetailsPojo> bloodDetails;
}
