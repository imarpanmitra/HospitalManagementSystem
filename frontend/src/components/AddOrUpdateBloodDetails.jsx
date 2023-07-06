import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import {
  addNewBloodDetails,
  retrieveAllBloodDetails,
  retrieveBloodDetailsById,
  updateBloodDetailsById,
} from "./api/ApiService";
import { useAuth } from "./security/AuthContext";

function AddOrUpdateBloodDetails() {
  const [name, setName] = useState("");
  const [age, setAge] = useState();
  const [sex, setSex] = useState("");
  const [bloodGroup, setBloodGroup] = useState("");
  const [donatedUnits, setDonatedUnits] = useState("");
  const [donationDate, setDonationDate] = useState("");

  const { id } = useParams();
  const navigate = useNavigate();

  const authContext = useAuth();

  useEffect(() => retrieveSpecificPatientDetails(), [id]);

  function retrieveSpecificPatientDetails() {
    if (id != -1) {
      retrieveBloodDetailsById(authContext.loggedinUserHospitalId, id)
        .then((response) => {
          console.log(response);
          setName(response.data.donorName);
          setSex(response.data.donorSex);
          setAge(response.data.donorAge);
          setBloodGroup(response.data.bloodGroup);
          setDonationDate(response.data.donationDate);
          setDonatedUnits(response.data.donatedUnits);
        })
        .catch((err) => {
          console.log(err);
        });
    }
  }

  function handleSubmit(event) {
    event.preventDefault();
    if (id != -1) {
      const bloodDetails = {
        bloodId: id,
        donorName: name,
        donorSex: sex,
        donorAge: age,
        bloodGroup: bloodGroup,
        donatedUnits: donatedUnits,
        donationDate: donationDate,
      };
      updateBloodDetailsById(
        authContext.loggedinUserHospitalId,
        id,
        bloodDetails
      ) //*
        .then()
        .catch()
        .finally();
      console.log(bloodDetails);
      retrieveAllBloodDetails(authContext.loggedinUserHospitalId);

      navigate("/bloodbank/blooddetails");
    } else {
      const bloodDetails = {
        donorName: name,
        donorSex: sex,
        donorAge: age,
        bloodGroup: bloodGroup,
        donatedUnits: donatedUnits,
        donationDate: donationDate,
      };
      addNewBloodDetails(bloodDetails, authContext.loggedinUserHospitalId)
        .then("Added")
        .catch("Error")
        .finally("finally");
      retrieveAllBloodDetails(authContext.loggedinUserHospitalId);
      navigate("/bloodbank/blooddetails");
    }
  }

  return (
    <div className="mt-5" style={{ minHeight: "78.1vh" }}>
      <div
        className="mb-5 pt-1 pb-3 container-fluid shadow bg-light"
        style={{ alignContent: "center", width: "550px", borderRadius: "10px" }}
      >
        <div className="container pt-3">
          <h2>
            <strong>Enter Blood Donation Details</strong>
          </h2>
        </div>
        <small className="text-danger text-xxs container">
          (All fields are mandatory){" "}
        </small>
        <form onSubmit={handleSubmit} className="regForm">
          <table className="container mb-3" style={{ textAlign: "left" }}>
            <tbody>
              <tr>
                <td>
                  <label htmlFor="name" className="pt-2 m-1">
                    Donor Name :{" "}
                  </label>
                </td>
                <td>
                  <input
                    type="text"
                    id="name"
                    value={name || ""}
                    onChange={(event) => setName(event.target.value)}
                    required
                  ></input>
                </td>
              </tr>

              <tr>
                <td>
                  <lable className="pt-2 m-1">Sex :</lable>
                </td>
                <td>
                  <input
                    type="radio"
                    id="M"
                    onChange={(event) => setSex(event.target.value)}
                    value="M"
                    checked={sex === "M"}
                    required
                  />{" "}
                  Male
                  <input
                    type="radio"
                    id="F"
                    onChange={(event) => setSex(event.target.value)}
                    value="F"
                    checked={sex === "F"}
                    required
                  />{" "}
                  Female
                  <input
                    type="radio"
                    id="O"
                    onChange={(event) => setSex(event.target.value)}
                    value="O"
                    checked={sex === "O"}
                    required
                  />
                  Other
                </td>
              </tr>

              <tr>
                <td>
                  <label htmlFor="age" className="pt-2 m-1">
                    Age :{" "}
                  </label>
                </td>
                <td>
                  <input
                    type="text"
                    id="age"
                    value={age}
                    onChange={(event) => setAge(event.target.value)}
                    required
                  ></input>
                </td>
              </tr>

              <tr>
                <td>
                  <label htmlFor="bedNo" className="pt-2 m-1">
                    Blood Group :{" "}
                  </label>
                </td>
                <td>
                  <select
                    id="bloodGroup"
                    value={bloodGroup}
                    onChange={(event) => setBloodGroup(event.target.value)}
                    className="m-2"
                    style={{
                      border: "1.5px solid grey",
                      borderRadius: "5px",
                      height: "28px",
                      width: "200px",
                    }}
                    required
                  >
                    <option>Select</option>
                    <option>A+</option>
                    <option>A-</option>
                    <option>B+</option>
                    <option>B-</option>
                    <option>AB+</option>
                    <option>AB-</option>
                    <option>O+</option>
                    <option>O-</option>
                  </select>
                </td>
              </tr>

              <tr>
                <td>
                  <label htmlFor="disease" className="pt-2 m-1">
                    Donoated Units :{" "}
                  </label>
                </td>
                <td>
                  <input
                    type="number"
                    id="donatedUnits"
                    value={donatedUnits}
                    onChange={(event) => setDonatedUnits(event.target.value)}
                    required
                  ></input>
                </td>
              </tr>

              <tr>
                <td>
                  <label htmlFor="admissionDate" className="pt-2 m-1">
                    Donation Date :{" "}
                  </label>
                </td>
                <td>
                  <input
                    type="date"
                    id="donationDate"
                    value={donationDate}
                    onChange={(event) => setDonationDate(event.target.value)}
                    required
                  ></input>
                </td>
              </tr>

              <div className="container m-3">
                <button type="submit" className="btn btn-primary btn-sm">
                  Submit Details
                </button>
                <button
                  type="button"
                  className="btn btn-secondary btn-sm"
                  onClick={() => navigate("/bloodbank/blooddetails")}
                >
                  Back to Blooddetails
                </button>
              </div>
            </tbody>
          </table>
        </form>
      </div>
    </div>
  );
}

export default AddOrUpdateBloodDetails;
