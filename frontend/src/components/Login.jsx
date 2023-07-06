import React from "react";
import { useState } from "react";
import {
  authenticateUser,
  retrieveSpecificHospitalById,
} from "./api/ApiService";
import { useAuth } from "./security/AuthContext";
import { Link, useNavigate } from "react-router-dom";

function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState(false);

  const authContext = useAuth();
  const navigate = useNavigate();

  async function handleSubmit(event) {
    event.preventDefault();

    try {
      const res = await authenticateUser(username, password);

      if (res.status === 200) {
        authContext.setIsLoggedin(true);
        authContext.setLoggedinUsername(res.data.firstName);
        authContext.setInvalidCredentialsMessage(false);

        navigate("/welcome");
      } else if (res.status !== 200) {
        authContext.setIsLoggedin(false);
        authContext.setLoggedinUsername(null);
        authContext.setInvalidCredentialsMessage(null);
        setError(true);
      }
    } catch {
      setError(true);
      authContext.setIsLoggedin(false);
      authContext.setLoggedinUsername(null);
      authContext.setInvalidCredentialsMessage(false);
    }

    authenticateUser(username, password).then((res) => {
      console.log(res);
      console.log(res.data.hospital.hospitalId);
      authContext.setLoggedinUserHospitalId(res.data.hospital.hospitalId);
      authContext.setLoggedinUserHospitalName(res.data.hospital.hospitalName);
      console.log(authContext.loggedinUserHospitalId);
      authContext.setIsLoggedin(true);
      authContext.setLoggedinUsername(res.data.employeeFirstName);

      retrieveSpecificHospitalById(res.data.hospital.hospitalId).then(
        (response) => authContext.setAvailableBed(response.data.availableBed)
      );

      console.log(
        "isloggedin: " +
          authContext.isLoggedin +
          "loggein username: " +
          authContext.loggedinUsername
      );
      authContext.setInvalidCredentialsMessage(false);
      console.log("hi");
    });
  }

  return (
    <div className="mt-4 p-5" style={{ minHeight: "calc(100vh - 140px)" }}>
      <div
        className="shadow container bg-light p-5"
        style={{ alignContent: "center", width: "400px", borderRadius: "10px" }}
      >
        <div>
          <div className="container">
            <h3>
              <strong> Log In </strong>
            </h3>
          </div>
          {error && (
            <div className="text-danger container">
              Invalid Username or Password
            </div>
          )}
          <form onSubmit={handleSubmit}>
            <div className="container">
              <input
                type="text"
                id="username"
                value={username}
                name="username"
                onChange={(event) => setUsername(event.target.value)}
                placeholder="Username"
                required
              />
            </div>
            <div className="container">
              <input
                type="password"
                id="password"
                value={password}
                name="password"
                onChange={(event) => setPassword(event.target.value)}
                placeholder="Password"
                required
              />
            </div>
            <div className="container">
              <button type="submit" className="btn btn-primary">
                Login
              </button>
            </div>
          </form>
          <div className="mt-2 container">
            Don't have an account?&nbsp;
            <Link to="/register">Register Here</Link>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Login;
