package com.cognizant.app.patientmanagement.pojo;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "hospital")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonFilter(HospitalPojo.FILTER)
public class HospitalPojo {
public static final String FILTER = "hospital-filter";
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long hospitalId;
	@Size(min = 5)
	@NotNull
	private String hospitalName;
	@Size(max = 3)
	@NotNull
	private String hospitalType;
	@NotNull
	private Integer availableBed;
	
	@NotNull
	private Boolean isBloodBank;
	
	@JsonIgnore
	@NotNull
	private String authorizationKeyHospitalSpecific;
	
	@OneToMany(mappedBy = "hospitalPojo")
	
	private List<PatientPojo> patients;
	
	@OneToMany(mappedBy = "hospitalPojo")
	
	private List<EmployeePojo> employees;
}
