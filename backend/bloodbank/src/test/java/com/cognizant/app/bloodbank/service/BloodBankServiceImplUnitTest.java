package com.cognizant.app.bloodbank.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.cognizant.app.bloodbank.controller.impl.BloodBankControllerImpl;
import com.cognizant.app.bloodbank.dto.HospitalDto;
import com.cognizant.app.bloodbank.pojo.BloodBankPojo;
import com.cognizant.app.bloodbank.repository.BloodBankRepository;
import com.cognizant.app.bloodbank.service.impl.BloodBankServiceImpl;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(BloodBankPojo.class)
public class BloodBankServiceImplUnitTest {
	
	@MockBean
	private BloodBankRepository bloodBankRepository;
	
	@MockBean
	private BloodBankControllerImpl bloodBankControllerImpl;
	
	@MockBean
	private RestTemplate restTemplate;
	
	@InjectMocks
	private BloodBankServiceImpl bloodBankServiceImpl;
	
	@Test
	public void retrieveBloodbankOfSpecificHospitalUnitTest() throws Exception {
		HospitalDto hospitalDto = new HospitalDto(1L, "Sample Hospital", "GOV", 500, true);
		ResponseEntity<HospitalDto> hospital = new ResponseEntity<HospitalDto>(hospitalDto, null, 200);
		
		BloodBankPojo bloodBankPojo = new BloodBankPojo(1L, "Sample Hospital BloodBank", "GOV", null);
		
		when(restTemplate.getForEntity("http://localhost:8080/hospitals/" + hospitalDto.getHospitalId(), 
				HospitalDto.class)).thenReturn(hospital);
		when(bloodBankRepository.findByHospitalId(anyLong())).thenReturn(bloodBankPojo);
		
		BloodBankPojo actualBloodBank = bloodBankServiceImpl.retrieveBloodbankOfSpecificHospital(1L);
		
		assertEquals(bloodBankPojo, actualBloodBank);
	}

}
