
insert into hospital (hospital_id, hospital_name, hospital_type, available_bed, is_blood_bank, authorization_key_hospital_specific) values(10001, 'R.G. Kar Medical College and Hospital', 'GOV', 598, 'true', 'B3A7K6Z9R2');
insert into hospital (hospital_id, hospital_name, hospital_type, available_bed, is_blood_bank, authorization_key_hospital_specific) values(10002, 'NRS Medical College and Hospital', 'GOV', 509, 'true', 'L8M4C1X6P9');
insert into hospital (hospital_id, hospital_name, hospital_type, available_bed, is_blood_bank, authorization_key_hospital_specific) values(10003, 'SureCure Super Speciality Hospital', 'PVT', 305, 'false', 'H2Y5D9J1V7');
insert into hospital (hospital_id, hospital_name, hospital_type, available_bed, is_blood_bank, authorization_key_hospital_specific) values(10004, 'MediCare Private Hospital', 'PVT', 355, 'false', 'N6Q3S7G4T8');
insert into hospital (hospital_id, hospital_name, hospital_type, available_bed, is_blood_bank, authorization_key_hospital_specific) values(10005, 'SSKM Medical College and Hospital', 'GOV', 377, 'true', 'F1W9E5O3I7');
insert into hospital (hospital_id, hospital_name, hospital_type, available_bed, is_blood_bank, authorization_key_hospital_specific) values(10006, 'Sagar Dutta Medical College and Hospital', 'GOV', 299, 'true', 'U4V9R6K2A8');
insert into hospital (hospital_id, hospital_name, hospital_type, available_bed, is_blood_bank, authorization_key_hospital_specific) values(10007, 'Zenith Super Speciality Hospital', 'PVT', 401, 'false', 'G5X1L7S3N9');



insert into employee (employee_id, first_name, last_name, sex, username, password, hospital_id) values (1001, 'Arpan', 'Mitra', 'M', 'arpan', 'abcd',  10001);
insert into employee (employee_id, first_name, last_name, sex, username, password, hospital_id) values (1004, 'Amit', 'Singh', 'M', 'singhamit123', 'abcd', 10001);
insert into employee (employee_id,  first_name, last_name, sex, username, password, hospital_id) values (1002, 'Scarlet', 'Witch', 'F', 'scarlet', 'abcd', 10002);
insert into employee (employee_id,  first_name, last_name, sex, username, password, hospital_id) values (1003, 'Anubrata', 'Kesto', 'M', 'anu123', 'abcd', 10003);


insert into patient (PATIENT_ID, PATIENT_NAME, PATIENT_SEX, PATIENT_AGE, BED_NO, DISEASE, ADMISSION_DATE, DOCTOR_NAME, LAST_VISITED, RELEASE_DATE, HOSPITAL_ID) values (1001, 'Madhurima Das', 'F',  33, 'ICU-201', 'Cardiac Attack', '2023-03-21',
				'Dr. K. Chakraborty', '01:16:18', '2023-04-24', 10001);
insert into patient (PATIENT_ID, PATIENT_NAME, PATIENT_SEX, PATIENT_AGE, BED_NO, DISEASE, ADMISSION_DATE, DOCTOR_NAME, LAST_VISITED, RELEASE_DATE, HOSPITAL_ID) values (1004, 'Lalu Yadav', 'M',  49, 'G-77', 'Appendix Operation', '2023-03-21',
				'Dr. Keshab Senapati', '01:16:18', '2023-04-24', 10001);
insert into patient (PATIENT_ID, PATIENT_NAME, PATIENT_SEX, PATIENT_AGE, BED_NO, DISEASE, ADMISSION_DATE, DOCTOR_NAME, LAST_VISITED, RELEASE_DATE, HOSPITAL_ID) values (1002, 'Hari Kumar Maity', 'M', 55, 'Ventilation-2', 'Kidney Stone', '2023-02-03',
				'Dr. K. Chakraborty', '01:16:18', '2023-05-18', 10002);
insert into patient (PATIENT_ID, PATIENT_NAME, PATIENT_SEX, PATIENT_AGE, BED_NO, DISEASE, ADMISSION_DATE, DOCTOR_NAME, LAST_VISITED, RELEASE_DATE, HOSPITAL_ID) values (1003, 'Chandan Gabriel', 'M', 38, 'General-461A', 'Dengue', '2023-03-26',
				'Dr. S. Dasgupta', '01:16:18', '2023-04-06', 10003);
                
insert into patient (PATIENT_ID, PATIENT_NAME, PATIENT_SEX, PATIENT_AGE, BED_NO, DISEASE, ADMISSION_DATE, DOCTOR_NAME, LAST_VISITED, RELEASE_DATE, HOSPITAL_ID) values (1005, 'Shankar Gopal Verma', 'M',  53, 'G-201', 'Emphysema', '2023-05-01',
				'Dr. M. Ganguly', '01:16:18', '2023-07-24', 10001);
insert into patient (PATIENT_ID, PATIENT_NAME, PATIENT_SEX, PATIENT_AGE, BED_NO, DISEASE, ADMISSION_DATE, DOCTOR_NAME, LAST_VISITED, RELEASE_DATE, HOSPITAL_ID) values (1006, 'Jatin Karmakar', 'M',  49, 'G-11', 'Bypass Surgery', '2023-05-29',
				'Dr. B. Das', '01:16:18', '2023-06-24', 10001);
insert into patient (PATIENT_ID, PATIENT_NAME, PATIENT_SEX, PATIENT_AGE, BED_NO, DISEASE, ADMISSION_DATE, DOCTOR_NAME, LAST_VISITED, RELEASE_DATE, HOSPITAL_ID) values (1007, 'Ranu Chatterjee', 'F',  39, 'G-70', 'L-Respiratory Infection', '2023-06-01',
				'Dr. G.K. Banerjee', '01:16:18', '2023-06-24', 10001);
insert into patient (PATIENT_ID, PATIENT_NAME, PATIENT_SEX, PATIENT_AGE, BED_NO, DISEASE, ADMISSION_DATE, DOCTOR_NAME, LAST_VISITED, RELEASE_DATE, HOSPITAL_ID) values (1008, 'Ajit Jha', 'M',  33, 'ICU-23', 'Cardiac Arrest', '2023-03-21',
				'Dr. J. Kumar', '01:16:18', '2023-06-29', 10001);
				
insert into patient (PATIENT_ID, PATIENT_NAME, PATIENT_SEX, PATIENT_AGE, BED_NO, DISEASE, ADMISSION_DATE, DOCTOR_NAME, LAST_VISITED, RELEASE_DATE, HOSPITAL_ID) values (1009, 'Shankar Gopal Verma', 'M',  53, 'G-201', 'Emphysema', '2023-05-01',
				'Dr. M. Ganguly', '01:16:18', '2023-07-24', 10001);
insert into patient (PATIENT_ID, PATIENT_NAME, PATIENT_SEX, PATIENT_AGE, BED_NO, DISEASE, ADMISSION_DATE, DOCTOR_NAME, LAST_VISITED, RELEASE_DATE, HOSPITAL_ID) values (1010, 'Jatin Karmakar', 'M',  49, 'G-11', 'Bypass Surgery', '2023-05-29',
				'Dr. B. Das', '01:16:18', '2023-06-24', 10001);
insert into patient (PATIENT_ID, PATIENT_NAME, PATIENT_SEX, PATIENT_AGE, BED_NO, DISEASE, ADMISSION_DATE, DOCTOR_NAME, LAST_VISITED, RELEASE_DATE, HOSPITAL_ID) values (1011, 'Ranu Chatterjee', 'F',  39, 'G-70', 'L-Respiratory Infection', '2023-06-01',
				'Dr. G.K. Banerjee', '01:16:18', '2023-06-24', 10001);
insert into patient (PATIENT_ID, PATIENT_NAME, PATIENT_SEX, PATIENT_AGE, BED_NO, DISEASE, ADMISSION_DATE, DOCTOR_NAME, LAST_VISITED, RELEASE_DATE, HOSPITAL_ID) values (1012, 'Ajit Jha', 'M',  33, 'ICU-23', 'Cardiac Arrest', '2023-03-21',
				'Dr. J. Kumar', '01:16:18', '2023-06-29', 10001);
				
				
insert into patient (PATIENT_ID, PATIENT_NAME, PATIENT_SEX, PATIENT_AGE, BED_NO, DISEASE, ADMISSION_DATE, DOCTOR_NAME, LAST_VISITED, RELEASE_DATE, HOSPITAL_ID) values (1013, 'Shankar Gopal Verma', 'M',  53, 'G-201', 'Emphysema', '2023-05-01',
				'Dr. M. Ganguly', '01:16:18', '2023-07-24', 10001);
insert into patient (PATIENT_ID, PATIENT_NAME, PATIENT_SEX, PATIENT_AGE, BED_NO, DISEASE, ADMISSION_DATE, DOCTOR_NAME, LAST_VISITED, RELEASE_DATE, HOSPITAL_ID) values (1014, 'Jatin Karmakar', 'M',  49, 'G-11', 'Bypass Surgery', '2023-05-29',
				'Dr. B. Das', '01:16:18', '2023-06-24', 10001);
insert into patient (PATIENT_ID, PATIENT_NAME, PATIENT_SEX, PATIENT_AGE, BED_NO, DISEASE, ADMISSION_DATE, DOCTOR_NAME, LAST_VISITED, RELEASE_DATE, HOSPITAL_ID) values (1015, 'Ranu Chatterjee', 'F',  39, 'G-70', 'L-Respiratory Infection', '2023-06-01',
				'Dr. G.K. Banerjee', '01:16:18', '2023-06-24', 10001);
insert into patient (PATIENT_ID, PATIENT_NAME, PATIENT_SEX, PATIENT_AGE, BED_NO, DISEASE, ADMISSION_DATE, DOCTOR_NAME, LAST_VISITED, RELEASE_DATE, HOSPITAL_ID) values (1016, 'Ajit Jha', 'M',  33, 'ICU-23', 'Cardiac Arrest', '2023-03-21',
				'Dr. J. Kumar', '01:16:18', '2023-06-29', 10001);
                

