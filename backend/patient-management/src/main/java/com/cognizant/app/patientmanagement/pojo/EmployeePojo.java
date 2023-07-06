package com.cognizant.app.patientmanagement.pojo;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee")
@JsonFilter(EmployeePojo.FILTER)
public class EmployeePojo {
public static final String FILTER = "employee-filter";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long employeeId;
	@Column(name = "firstName")
	private String employeeFirstName;
	@Column(name = "lastName")
	private String employeeLastName;
	@Column(name = "sex")
	private String employeeSex;	
	private String username;
	private String password;
	@Transient
	private String highSecurityPassword;
	
	@Transient
	private String selectedHospital;
	
	@ManyToOne
	@JoinColumn(name = "hospitalId")
	@JsonProperty(value = "hospital")
	private HospitalPojo hospitalPojo;
}
