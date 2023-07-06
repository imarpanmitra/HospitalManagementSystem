import { clientApi } from "./ApiClient";

export const authenticateUser = (username, password) =>
  clientApi.post(`/authenticate`, { username, password });

export const addNewUser = (userdetails) =>
  clientApi.post(`/employees`, userdetails);

export const retrieveAllPatients = (hospitalId) =>
  clientApi.get(`/hospitals/${hospitalId}/patients`);

export const deletePatientDetailsById = (hospitalId, id) =>
  clientApi.delete(`/hospitals/${hospitalId}/patients/${id}`);

export const retrievePatientDetailsById = (hospitalId, patientId) =>
  clientApi.get(`/hospitals/${hospitalId}/patients/${patientId}`);

export const updatePatientDetailsById = (
  hospitalId,
  patientId,
  patientDetails
) =>
  clientApi.put(
    `/hospitals/${hospitalId}/patients/${patientId}`,
    patientDetails
  );

export const addNewPatientdetails = (patientDetails, hospitalId) =>
  clientApi.post(`/hospitals/${hospitalId}/patients`, patientDetails);

export const retrieveAllHospitals = () => clientApi.get(`/hospitals`);

export const retrieveSpecificHospitalById = (hospitalId) =>
  clientApi.get(`/hospitals/${hospitalId}`);

export const retrieveAllBloodDetails = (hospitalId) =>
  clientApi.get(`/bloodbank/${hospitalId}/blooddetails`);

export const deleteBloodDetailsById = (hospitalId, bloodId) =>
  clientApi.delete(`/bloodbank/${hospitalId}/blooddetails/${bloodId}`);

export const retrieveBloodDetailsById = (hospitalId, bloodId) =>
  clientApi.get(`/bloodbank/${hospitalId}/blooddetails/${bloodId}`);

export const addNewBloodDetails = (bloodDetails, hospitalId) =>
  clientApi.post(`/bloodbank/${hospitalId}/blooddetails`, bloodDetails);

export const updateBloodDetailsById = (hospitalId, bloodId, bloodDetails) =>
  clientApi.put(
    `/bloodbank/${hospitalId}/blooddetails/${bloodId}`,
    bloodDetails
  );

export const enableBloodBankById = (hospitalId) =>
  clientApi.post(`/bloodbank/${hospitalId}/enable/bloodbank`);
