import React, { useEffect } from "react";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import {
  enableBloodBankById,
  retrieveSpecificHospitalById,
} from "./api/ApiService";
import { useAuth } from "./security/AuthContext";

function Welcome() {
  const navigate = useNavigate();
  const authContext = useAuth();
  const [isBloodBankConst, setIsBloodbankConst] = useState(false);

  function renderAvailableBed() {
    retrieveSpecificHospitalById(authContext.loggedinUserHospitalId).then(
      (res) => authContext.setAvailableBed(res.data.availableBed)
    );
  }

  useEffect(() => renderAvailableBed(), []);

  function enableBloodBank(hospitalId) {
    enableBloodBankById(hospitalId);
    alert(
      "Blood Bank created for " +
        authContext.loggedinUserHospitalName +
        " Please login again to manage bloodbank"
    );
    navigate("/logout");
  }

  function renderBloodbankButton() {
    retrieveSpecificHospitalById(authContext.loggedinUserHospitalId).then(
      (res) => {
        setIsBloodbankConst(res.data.isBloodBank);
      }
    );
    if (isBloodBankConst == true) {
      return (
        <div className="container">
          <button
            className="btn btn-secondary btn-md m-2"
            onClick={() => navigate("/bloodbank/blooddetails")}
          >
            Manage Blood Bank
          </button>
        </div>
      );
    } else
      return (
        <div>
          <div className="text-danger container">
            Your hospital doesn't have a bloodbank. Enable Blood Bank?
          </div>
          <div className="container">
            <button
              className="btn btn-secondary btn-md m-2"
              onClick={() =>
                enableBloodBank(authContext.loggedinUserHospitalId)
              }
            >
              Enable Blood Bank
            </button>
          </div>
        </div>
      );
  }

  return (
    <div style={{ minHeight: "calc(100vh - 164px)" }}>
      <div
        className="shadow p-5 mt-5 bg-light container-fluid"
        style={{
          alignContent: "center",
          width: "1000px",
          borderRadius: "10px",
        }}
      >
        <div className="container pt-3">
          <h3 className="m">
            Hi, <strong>{authContext.loggedinUsername}</strong>.&nbsp;
          </h3>
          <h3>Welcome to</h3>
        </div>
        <div className="container pb-3">
          <h2 className="text-danger">
            <strong>{authContext.loggedinUserHospitalName}</strong>
          </h2>
        </div>
        <div
          className="container p-1 mb-3 "
          style={{
            alignContent: "center",
            width: "180px",
            border: "3px solid red",
            borderRadius: "5px",
          }}
        >
          <strong>Available Bed:</strong>&nbsp;
          <text className="text-danger">
            <b>{authContext.availableBed}</b>
          </text>
        </div>
        <div className="container">
          <button
            className="btn btn-primary btn-md m-2"
            onClick={() => navigate("/patients")}
          >
            Manage Patient Details
          </button>
        </div>
        {renderBloodbankButton()}
      </div>
    </div>
  );
}

export default Welcome;
