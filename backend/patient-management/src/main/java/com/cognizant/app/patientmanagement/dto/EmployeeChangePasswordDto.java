package com.cognizant.app.patientmanagement.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeChangePasswordDto {
	@NotNull
	private String oldPassword;
	@NotEmpty
	@Size(min = 4, message = "Minimum 4 characters")
	private String newPassword;
	@NotEmpty
	@Size(min = 4, message = "Minimum 4 characters")
	private String confirmNewPassword;
}
