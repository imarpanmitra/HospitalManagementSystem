package com.cognizant.app.patientmanagement.pojo;

import java.time.LocalDate;
import java.time.LocalTime;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
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
@Table(name = "patient")
@JsonFilter(PatientPojo.FILTER)
public class PatientPojo {
	public static final String FILTER = "patient-filter";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer patientId;

	@Size(min = 5, message = "Name should be of atleast 5 characters")
	private String patientName;
	@Length(max = 1)
	@Pattern(regexp = "[FMO]", message = "Invalid sex value. Only 'F', 'M', or 'O' are allowed.")
	private String patientSex;
	@PositiveOrZero(message = "Age Must be greater than or equals zero")
	@NotNull
	@Max(message ="Age Must be less than 117 years", value = 117)
	private Integer patientAge;
	@NotNull
	private String bedNo;
	@Size(min = 3, message = "Disease should be of atleast 3 characters")
	private String disease;
	@PastOrPresent(message = "Admission date should be a past or present value")
	private LocalDate admissionDate;
	@Size(min = 5, message = "Doctor name should be of atleast 5 characters")
	private String doctorName;
	@DateTimeFormat(pattern = "HH:MM:SS")
	@NotNull
	private LocalTime lastVisited;
	@FutureOrPresent(message = "Release date should be a future or present value")
	private LocalDate releaseDate;

	@ManyToOne
	@JoinColumn(name = "hospitalId")
	@JsonProperty(value = "hospital")
	private HospitalPojo hospitalPojo;
}
