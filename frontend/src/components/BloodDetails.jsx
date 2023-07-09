import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import {
  deleteBloodDetailsById,
  retrieveAllBloodDetails,
} from "./api/ApiService";
import { useAuth } from "./security/AuthContext";
import { Dialog, DialogTitle } from "@mui/material";

function BloodDetailsList() {
  const navigate = useNavigate();
  const authContext = useAuth();

  const [bloodDetails, setBlooddetails] = useState([]);
  const [message, setMessage] = useState(null);
  const [openDialog, setOpenDialog] = useState(false);

  const handleClose = () => {
    setOpenDialog(false);
  };

  function renderBloodDetails() {
    retrieveAllBloodDetails(authContext.loggedinUserHospitalId)
      .then((res) => {
        setBlooddetails(res.data);
      })
      .catch((err) => {
        console.log(err);
      });
  }
  useEffect(() => renderBloodDetails(), []);

  function updatePatientDetails(bloodId) {
    navigate(`/bloodbank/blooddetails/${bloodId}`);
  }

  function deleteBloodDetails(bloodId) {
    deleteBloodDetailsById(authContext.loggedinUserHospitalId, bloodId).then(
      (response) => {
        if (response.status === 204) {
          setMessage("Blood Donation details has been deleted");
          setOpenDialog(true);
          renderBloodDetails();
        }
      }
    );
  }

  return (
    <div className="list-items" style={{ minHeight: "82.5vh" }}>
      <div className="container mb-1">
        <h2>
          <strong>Blood Donation Details</strong>
        </h2>
      </div>

      {message && (
        <div>
          <Dialog onClose={handleClose} open={openDialog}>
            <DialogTitle>Attention {authContext.loggedinUsername}</DialogTitle>
            <h4 style={{ padding: "10px" }}>{message}</h4>
          </Dialog>
        </div>
      )}
      <div>
        <div className="shadow-sm table-responsive">
          <table className="table">
            <thead>
              <tr>
                <th>ID</th>
                <th>Donor Name</th>
                <th>Blood Group</th>
                <th>Donor Sex</th>
                <th>Donor Age</th>
                <th>Donation Date</th>
                <th>Donated Units</th>
                <th> </th>
                <th> </th>
              </tr>
            </thead>
            <tbody>
              {bloodDetails.map((blood) => (
                <tr key={blood.bloodId}>
                  <td>{blood.bloodId}</td>
                  <td>{blood.donorName}</td>
                  <td>{blood.bloodGroup}</td>
                  <td>{blood.donorSex}</td>
                  <td>{blood.donorAge}</td>
                  <td>{blood.donationDate.toString()}</td>
                  <td>{blood.donatedUnits}</td>
                  <td>
                    <button
                      className="btn btn-danger btn-sm m-0"
                      onClick={() => deleteBloodDetails(blood.bloodId)}
                    >
                      Delete
                    </button>
                  </td>
                  <td>
                    <button
                      className="btn btn-success btn-sm m-0"
                      onClick={() => updatePatientDetails(blood.bloodId)}
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
            className="btn btn-primary btn-sm m-2"
            onClick={() => navigate("/bloodbank/blooddetails/-1")}
          >
            Add New Blood Details
          </button>
        </div>
        <div className="container gap-2 col-6 mx-auto">
          <button
            type="button"
            className="btn btn-danger btn-sm mb-3"
            onClick={() => navigate("/bloodbank/bloodunits")}
          >
            Blood Availability
          </button>
        </div>
      </div>
    </div>
  );
}

export default BloodDetailsList;
