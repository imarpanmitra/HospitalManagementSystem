import { width } from "@mui/system";
import React, { useState } from "react";
import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { addNewUser, retrieveAllHospitals } from "./api/ApiService";
import { ToastContainer, toast, Slide } from "react-toastify";

function ResgistrationForm() {
  const [username, setUsername] = useState("");
  const [firstName, setFirstName] = useState("");
  const [lastName, setLastName] = useState("");
  const [sex, setSex] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [errors, setErrors] = useState(false);
  const [highSecurityPassword, setHighSecurityPassword] = useState("");
  const [hospitals, setHospitals] = useState([]);
  const [selectedHospital, setSelectedHospital] = useState("");

  const navigate = useNavigate();

  function renderHospitals() {
    retrieveAllHospitals().then((res) => {
      setHospitals(res.data);
    });
  }

  useEffect(() => renderHospitals, []);

  const handleSubmit = (event) => {
    event.preventDefault();
    const employeeDetails = {
      employeeFirstName: firstName,
      employeeLastName: lastName,
      employeeSex: sex,
      username: username,
      password: password,
      highSecurityPassword: highSecurityPassword,
      selectedHospital: selectedHospital,
    };
    if (password === confirmPassword) {
      addNewUser(employeeDetails)
        .then(() => {
          toast.success(
            "Hi " +
              firstName +
              ". Your account has been created. Time to Login. We are redirecting you to login page",
            {
              position: toast.POSITION.TOP_CENTER,
              autoClose: 3500,
              hideProgressBar: false,
              closeOnClick: true,
              pauseOnHover: true,
              draggable: true,
              transition: Slide,
              theme: "colored",
            }
          );
          setTimeout(function () {
            navigate("/login");
          }, 2000);
        })
        .catch((err) => {
          if (err.response.status === 401) {
            toast.error(err.response.data.message, {
              position: toast.POSITION.TOP_CENTER,
              autoClose: 3000, //3 seconds
              hideProgressBar: false,
              closeOnClick: true,
              pauseOnHover: true,
              draggable: true,
              transition: Slide,
              theme: "colored",
            });
          } else {
            toast.warning(err.response.data.message, {
              position: toast.POSITION.TOP_CENTER,
              autoClose: 3000, //3 seconds
              hideProgressBar: false,
              closeOnClick: true,
              pauseOnHover: true,
              draggable: true,
              transition: Slide,
              theme: "colored",
            });
          }
        });
    } else {
      toast.warning("Password should match with Confirm Password field", {
        position: toast.POSITION.TOP_CENTER,
        autoClose: 3000, //3 seconds
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        transition: Slide,
        theme: "colored",
      });
    }
  };

  return (
    <div style={{ minHeight: "78.2vh" }}>
      <ToastContainer></ToastContainer>
      <div
        className="container-fluid shadow bg-light p-3 mt-5"
        style={{ alignContent: "center", width: "500px", borderRadius: "10px" }}
      >
        <div className="container">
          <h2>
            <strong>Registration</strong>
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
                  <label htmlFor="firstName">First Name:</label>
                </td>
                <td>
                  {" "}
                  <input
                    type="text"
                    id="firstName"
                    value={firstName}
                    onChange={(event) => setFirstName(event.target.value)}
                    required
                  />
                </td>
              </tr>
              <tr>
                <td>
                  <label htmlFor="lastName" className="pt-2 m-1">
                    Last Name:
                  </label>
                </td>
                <td>
                  <input
                    type="text"
                    id="lastName"
                    value={lastName}
                    onChange={(event) => setLastName(event.target.value)}
                    required
                  />
                </td>
              </tr>
              <tr>
                <td>
                  <label htmlFor="sex" className="p-1 m-1">
                    Sex:
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
                  <label htmlFor="hospitals" className="p-1 m-1">
                    Hospital:{" "}
                  </label>
                </td>
                <td>
                  <select
                    name="hospitals"
                    id="hospitals"
                    className="m-2"
                    style={{
                      border: "1.5px solid grey",
                      borderRadius: "5px",
                      height: "28px",
                      width: "200px",
                    }}
                    onChange={(e) => setSelectedHospital(e.target.value)}
                  >
                    <option>----Select your hospital----</option>
                    {hospitals.map((hospital) => (
                      <option style={{ textAlign: "center" }}>
                        {hospital.hospitalName}
                      </option>
                    ))}
                  </select>
                </td>
              </tr>
              <tr>
                <td>
                  <label htmlFor="username" className="pt-2 m-1">
                    Username:
                  </label>
                </td>
                <td>
                  <input
                    type="text"
                    id="regUsername"
                    value={username}
                    onChange={(event) => setUsername(event.target.value)}
                    required
                  />
                </td>
              </tr>
              <tr>
                <td>
                  <label htmlFor="password" className="pt-2 m-1">
                    Password:
                  </label>
                </td>
                <td>
                  <input
                    type="password"
                    id="regPassword"
                    value={password}
                    onChange={(event) => setPassword(event.target.value)}
                    required
                  />
                </td>
              </tr>
              <tr>
                <td>
                  <label htmlFor="confirmPassword" className="pt-2 m-1">
                    Confirm Password:
                  </label>
                </td>
                <td>
                  {" "}
                  <input
                    type="password"
                    id="regConfirmPassword"
                    value={confirmPassword}
                    onChange={(event) => setConfirmPassword(event.target.value)}
                  />
                </td>
              </tr>
              <tr>
                <td>
                  <label htmlFor="highSecurityPassword" className="pt-2 m-1">
                    Authorization key:
                  </label>
                </td>
                <td>
                  {" "}
                  <input
                    type="password"
                    id="highSecurityPassword"
                    value={highSecurityPassword}
                    onChange={(event) =>
                      setHighSecurityPassword(event.target.value)
                    }
                    placeholder="To be provided by hospital"
                  />
                </td>
              </tr>
            </tbody>
          </table>
          <div className="container">
            <button type="submit" className="btn btn-primary">
              Register
            </button>

            <button
              type="button"
              className="btn btn-secondary btn"
              onClick={() => navigate("/login")}
            >
              Back to Login
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}

export default ResgistrationForm;
