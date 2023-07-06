import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import {
  retrieveAllPatients,
  deletePatientDetailsById,
} from "./api/ApiService";
import { useAuth } from "./security/AuthContext";
import { Dialog, DialogTitle } from "@mui/material";

function PatientList() {
  const navigate = useNavigate();
  const authContext = useAuth();

  const [patientDetails, setPatientdetails] = useState([]);
  const [message, setMessage] = useState(null);
  const [openDialog, setOpenDialog] = useState(false);

  const handleClose = () => {
    setOpenDialog(false);
    navigate("/patients");
  };

  function renderPatientDetails() {
    retrieveAllPatients(authContext.loggedinUserHospitalId)
      .then((res) => {
        setPatientdetails(res.data);
        console.log(patientDetails);
      })
      .catch((err) => {
        console.log(err);
      });
  }
  useEffect(() => renderPatientDetails(), []);

  function updatePatientDetails(id) {
    navigate(`/patients/${id}`);
  }

  function deletePatientDetails(id) {
    deletePatientDetailsById(authContext.loggedinUserHospitalId, id).then(
      (response) => {
        console.log(response);
        if (response.status === 204) {
          setMessage("Patient has been deleted");
          setOpenDialog(true);
          renderPatientDetails();
        }
      }
    );
  }

  return (
    <div style={{ minHeight: "82.5vh" }} className="container-fluid mb-3">
      <div className="container mb-1">
        <h2>
          <strong>Patient Details</strong>
        </h2>
      </div>

      {message && (
        <div>
          <Dialog onClose={handleClose} open={openDialog}>
            <DialogTitle className="text-danger">
              Attention {authContext.loggedinUsername}
            </DialogTitle>
            <h4 style={{ padding: "10px" }}>{message}</h4>
          </Dialog>
        </div>
      )}
      <div>
        <div className="shadow shadow-sm table-responsive">
          <table className="table">
            <thead>
              <tr>
                <th>Patient ID</th>
                <th>Patient Name</th>
                <th>Age</th>
                <th>Sex</th>
                <th>Bed No.</th>
                <th>Reason of Admission</th>
                <th>Assigned Doctor</th>
                <th>Last Visited</th>
                <th>Admission Date</th>
                <th>Release Date</th>
                <th> </th>
                <th> </th>
              </tr>
            </thead>
            <tbody>
              {patientDetails.map((patient) => (
                <tr key={patient.patientId}>
                  <td>{patient.patientId}</td>
                  <td>{patient.patientName}</td>
                  <td>{patient.patientAge}</td>
                  <td>{patient.patientSex}</td>
                  <td>{patient.bedNo}</td>
                  <td>{patient.disease}</td>
                  <td>{patient.doctorName}</td>
                  <td>{patient.lastVisited}</td>
                  <td>{patient.admissionDate.toString()}</td>
                  <td>{patient.releaseDate.toString()}</td>
                  <td>
                    <button
                      className="btn btn-danger btn-sm m-0"
                      onClick={() => deletePatientDetails(patient.patientId)}
                    >
                      Delete
                    </button>
                  </td>
                  <td>
                    <button
                      className="btn btn-success btn-sm m-0"
                      onClick={() => updatePatientDetails(patient.patientId)}
                    >
                      Update
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
        <div className="container gap-2 col-6 mx-auto mt-2">
          <button
            type="button"
            className="btn btn-primary btn-sm"
            onClick={() => navigate("/patients/-1")}
          >
            Add New Patient
          </button>
        </div>
        <div className="container"></div>
      </div>
    </div>
  );
}

export default PatientList;
