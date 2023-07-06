import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import {
  addNewPatientdetails,
  retrieveAllPatients,
  retrievePatientDetailsById,
  updatePatientDetailsById,
} from "./api/ApiService";
import { useAuth } from "./security/AuthContext";

function AddOrUpdatePatient() {
  const [name, setName] = useState("");
  const [age, setAge] = useState();
  const [sex, setSex] = useState("");
  const [disease, setDisease] = useState("");
  const [doctorName, setDoctorName] = useState("");
  const [bedNo, setBedNo] = useState("");
  const [admissionDate, setAdmissionDate] = useState("");
  const [lastVisited, setLastVisited] = useState("");
  const [releaseDate, setReleasedate] = useState("");

  const { id } = useParams();
  const navigate = useNavigate();

  const authContext = useAuth();

  useEffect(() => retrieveSpecificPatientDetails(), [id]);

  function retrieveSpecificPatientDetails() {
    if (id != -1) {
      retrievePatientDetailsById(authContext.loggedinUserHospitalId, id)
        .then((response) => {
          setName(response.data.patientName);
          setSex(response.data.patientSex);
          setAge(response.data.patientAge);
          setBedNo(response.data.bedNo);
          setDisease(response.data.disease);
          setDoctorName(response.data.doctorName);
          setAdmissionDate(response.data.admissionDate);
          setLastVisited(response.data.lastVisited);
          setReleasedate(response.data.releaseDate);
        })
        .catch((err) => {
          console.log(err);
        });
    }
  }

  async function handleSubmit(event) {
    event.preventDefault();
    if (id != -1) {
      const patientDetails = {
        patientId: id,
        patientName: name,
        patientSex: sex,
        patientAge: age,
        bedNo: bedNo,
        doctorName: doctorName,
        disease: disease,
        admissionDate: admissionDate,
        lastVisited: lastVisited,
        releaseDate: releaseDate,
      };
      await updatePatientDetailsById(
        authContext.loggedinUserHospitalId,
        id,
        patientDetails
      )
        .then((res) => {
          if (res.status === 201) {
            retrieveAllPatients(authContext.loggedinUserHospitalId);
            navigate("/patients");
          }
        })
        .catch((err) => {
          err.response.data.message && alert(err.response.data.message);
        })
        .finally();
      console.log(patientDetails);
      retrieveAllPatients(authContext.loggedinUserHospitalId);
    } else {
      const patientDetails = {
        patientName: name,
        patientSex: sex,
        patientAge: age,
        bedNo: bedNo,
        doctorName: doctorName,
        disease: disease,
        admissionDate: admissionDate,
        lastVisited: lastVisited,
        releaseDate: releaseDate,
      };
      await addNewPatientdetails(
        patientDetails,
        authContext.loggedinUserHospitalId
      )
        .then((res) => {
          if (res.status === 201) {
            navigate("/patients");
          }
        })
        .catch((err) => {
          err.response.data.message && alert(err.response.data.message);
        });
      // console.log(res.status);
    }
  }

  return (
    <div className="mt-4" style={{ minHeight: "81.3vh" }}>
      <div
        className="mb-5 pt-1 pb-3 container-fluid shadow bg-light"
        style={{ alignContent: "center", width: "510px", borderRadius: "10px" }}
      >
        <div className="container mt-3">
          <h2>
            <strong>Enter Patient Details</strong>
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
                    Patient Name :{" "}
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
                  <label htmlFor="name" className="pt-2 m-1">
                    Sex :
                  </label>
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
                    type="number"
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
                    Bed No. :{" "}
                  </label>
                </td>
                <td>
                  <input
                    type="text"
                    id="bedNo"
                    value={bedNo}
                    onChange={(event) => setBedNo(event.target.value)}
                    required
                  ></input>
                </td>
              </tr>

              <tr>
                <td>
                  <label htmlFor="disease" className="pt-2 m-1">
                    Reason For Admission :{" "}
                  </label>
                </td>
                <td>
                  <input
                    type="text"
                    id="disease"
                    value={disease}
                    onChange={(event) => setDisease(event.target.value)}
                    required
                  ></input>
                </td>
              </tr>

              <tr>
                <td>
                  <label htmlFor="admissionDate" className="pt-2 m-1">
                    Admission Date :{" "}
                  </label>
                </td>
                <td>
                  <input
                    type="date"
                    id="name"
                    value={admissionDate}
                    onChange={(event) => setAdmissionDate(event.target.value)}
                    required
                  ></input>
                </td>
              </tr>
              <tr>
                <td>
                  <label htmlFor="doctorName" className="pt-2 m-1">
                    Assigned Doctor :{" "}
                  </label>
                </td>
                <td>
                  <input
                    type="text"
                    id="doctorName"
                    value={doctorName}
                    onChange={(event) => setDoctorName(event.target.value)}
                    required
                  ></input>
                </td>
              </tr>

              <tr>
                <td>
                  <label htmlFor="lastVisited" className="pt-2 m-1">
                    Last Visited :{" "}
                  </label>
                </td>
                <td>
                  <input
                    type="time"
                    step="2"
                    id="lastVisited"
                    value={lastVisited}
                    onChange={(event) => setLastVisited(event.target.value)}
                    required
                    placeholder="HH:MM:SS"
                  ></input>
                </td>
              </tr>

              <tr>
                <td>
                  <label htmlFor="releaseDate" className="pt-2 m-1">
                    Release Date :{" "}
                  </label>
                </td>
                <td>
                  <input
                    type="date"
                    id="releaseDate"
                    value={releaseDate}
                    onChange={(event) => setReleasedate(event.target.value)}
                    required
                  ></input>
                </td>
              </tr>
            </tbody>
          </table>
          <div className="container m-3">
            <button type="submit" className="btn btn-primary btn-sm">
              Submit Details
            </button>
            <button
              type="button"
              className="btn btn-secondary btn-sm"
              onClick={() => navigate("/patients")}
            >
              Back to Patients
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}

export default AddOrUpdatePatient;
