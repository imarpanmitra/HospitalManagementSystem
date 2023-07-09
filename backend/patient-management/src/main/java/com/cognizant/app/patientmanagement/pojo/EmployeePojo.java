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
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
	@Size(min = 3, max = 20, message = "Fisrt Name should be of atleast 3 characters")
	@NotEmpty(message = "Name should not be empty and atleast 5 characters long")
	private String employeeFirstName;

	@Size(min = 2, max = 20, message = "Last Name should be of atleast 2 characters")
	@NotEmpty(message = "Name should not be empty and atleast 5 characters long")
	@Column(name = "lastName")
	private String employeeLastName;

	@Column(name = "sex")
	@Size(max = 1, min = 1, message = "Sex can contain max 1 character. e.g: 'F', 'M' or 'O")
	@Pattern(regexp = "[FMO]", message = "Invalid sex value. Only 'F', 'M', or 'O' are allowed.")
	@NotEmpty(message = "Sex can't be empty")
	private String employeeSex;

	@NotEmpty(message = "Username can't be empty")
	@Size(min = 5, max = 20, message = "Username should be atleast 5 and atmost 20 characters long")
	private String username;
	
	@NotEmpty(message = "Password can't be empty")
	@Size(min = 4, message = "Password should be atleast 4 characters long")
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
