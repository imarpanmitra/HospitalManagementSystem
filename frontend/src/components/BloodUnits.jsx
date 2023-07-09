import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import { retrieveAllBloodDetails } from "./api/ApiService";
import { useAuth } from "./security/AuthContext";

function BloodUnits() {
  const authContext = useAuth();
  const [aPositive, setAPositive] = useState(0);
  const [aNegative, setANegative] = useState(0);
  const [bPositive, setBPositive] = useState(0);
  const [bNegative, setBNegative] = useState(0);
  const [abPositive, setAbPositive] = useState(0);
  const [abNegative, setAbNegative] = useState(0);
  const [oPositive, setOPositive] = useState(0);
  const [oNegative, setONegative] = useState(0);

  const [bloodDetails, setBloodDetails] = useState([]);

  const navigate = useNavigate();

  function renderBloodDetails() {
    retrieveAllBloodDetails(authContext.loggedinUserHospitalId)
      .then((res) => {
        setBloodDetails(res.data);
        console.log(res.data);
        calculateAllBloodUnits(res.data);
      })
      .catch((err) => console.log(err));
  }

  useEffect(() => renderBloodDetails, []);

  function calculateAllBloodUnits(response) {
    console.log(response);
    response.forEach((blood) => {
      if (blood.bloodGroup === "A+")
        return setAPositive((x) => x + blood.donatedUnits);
      if (blood.bloodGroup === "B+")
        return setBPositive((x) => x + blood.donatedUnits);
      if (blood.bloodGroup === "AB+")
        return setAbPositive((x) => x + blood.donatedUnits);
      if (blood.bloodGroup === "O+")
        return setOPositive((x) => x + blood.donatedUnits);
      if (blood.bloodGroup === "A-")
        return setANegative((x) => x + blood.donatedUnits);
      if (blood.bloodGroup === "B-")
        return setBNegative((x) => x + blood.donatedUnits);
      if (blood.bloodGroup === "AB-")
        return setAbNegative((x) => x + blood.donatedUnits);
      if (blood.bloodGroup === "O-")
        return setONegative((x) => x + blood.donatedUnits);
    });
  }

  return (
    <div style={{ height: "calc(100vh - 133px)" }} className="pt-5">
      <div className="container blood-units">
        <table className="shadow">
          <thead>
            <tr
              className="shadow-sm bg-danger text-light"
              style={{ border: "0.5px solid grey" }}
            >
              <td className="p-4" style={{ border: "0.5px solid grey" }}>
                <strong>A+</strong>
              </td>
              <td className="p-4" style={{ border: "0.5px solid grey" }}>
                <strong>B+</strong>
              </td>
              <td className="p-4" style={{ border: "0.5px solid grey" }}>
                <strong>AB+</strong>
              </td>
              <td className="p-4" style={{ border: "0.5px solid grey" }}>
                <strong>O+</strong>
              </td>
            </tr>
          </thead>
          <tbody>
            <tr style={{ border: "0.5px solid grey" }}>
              <td className="p-4" style={{ border: "0.5px solid grey" }}>
                {aPositive}
              </td>
              <td className="p-4" style={{ border: "0.5px solid grey" }}>
                {bPositive}
              </td>
              <td className="p-4" style={{ border: "0.5px solid grey" }}>
                {abPositive}
              </td>
              <td className="p-4" style={{ border: "0.5px solid grey" }}>
                {oPositive}
              </td>
            </tr>
          </tbody>
          <thead>
            <tr
              className="shadow-sm bg-danger text-light"
              style={{ border: "0.5px solid grey" }}
            >
              <td className="p-4" style={{ border: "0.5px solid grey" }}>
                <strong>A-</strong>
              </td>
              <td className="p-4" style={{ border: "0.5px solid grey" }}>
                <strong>B-</strong>
              </td>
              <td className="p-4" style={{ border: "0.5px solid grey" }}>
                <strong>AB-</strong>
              </td>
              <td className="p-4" style={{ border: "0.5px solid grey" }}>
                <strong>O-</strong>
              </td>
            </tr>
          </thead>
          <tbody>
            <tr style={{ border: "0.5px solid grey" }}>
              <td className="p-4" style={{ border: "0.5px solid grey" }}>
                {aNegative}
              </td>
              <td className="p-4" style={{ border: "0.5px solid grey" }}>
                {bNegative}
              </td>
              <td className="p-4" style={{ border: "0.5px solid grey" }}>
                {abNegative}
              </td>
              <td className="p-4" style={{ border: "0.5px solid grey" }}>
                {oNegative}
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <div className="container">
        <button
          type="button"
          className="btn btn-primary btn-sm m-5"
          onClick={() => navigate("/bloodbank/blooddetails")}
        >
          Back to Blooddetails
        </button>
      </div>
    </div>
  );
}

export default BloodUnits;
