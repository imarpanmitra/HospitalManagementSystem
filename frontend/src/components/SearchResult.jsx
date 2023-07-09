import { Dialog, DialogTitle } from "@mui/material";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { deletePatientDetailsById } from "./api/ApiService";
import { useAuth } from "./security/AuthContext";

function SearchResult() {

const authContext = useAuth();
const navigate = useNavigate();
const [message, setMessage] = useState(null);
const [openDialog, setOpenDialog] = useState(false);

function deletePatientDetails(id) {
    deletePatientDetailsById(authContext.loggedinUserHospitalId, id).then((response) => {
        console.log(response);
        if (response.status === 204) {
          setMessage("Patient has been deleted");
          setOpenDialog(true);
        }
      });
}

function updatePatientDetails(id) {
    navigate(`/patients/${id}`);
}

const handleClose = () => {
    setOpenDialog(false);
    navigate("/patients");
  };

    return (
        <div style={{height: 'calc(100vh - 133px)'}}>
            <div className="container"><h2><strong>Search Results</strong></h2></div>
            {message && (
                <div>
                    <Dialog onClose={handleClose} open={openDialog}>
                        <DialogTitle>Attention {authContext.loggedinUsername}</DialogTitle>
                        <h4 style={{ padding: "10px" }}>{message}</h4>
                    </Dialog>
                </div>
            )}
        <div className="container-xs" >
            {/* {(authContext.searchResults).map((result) => <div>{result.name}</div>)}; */}
            <table className="table">
          <thead>
            <tr>
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
            {authContext.searchResults.map((patient) => (
              <tr key={patient.id}>
                <td>{patient.name}</td>
                <td>{patient.age}</td>
                <td>{patient.sex}</td>
                <td>{patient.bedNo}</td>
                <td>{patient.disease}</td>
                <td>{patient.doctorName}</td>
                <td>{patient.lastVisited}</td>
                <td>{patient.admissionDate.toString()}</td>
                <td>{patient.releaseDate.toString()}</td>
                <td>
                  <button
                    className="btn btn-danger btn-sm m-0"
                    onClick={() => deletePatientDetails(patient.id)}
                  >
                    Delete
                  </button>
                </td>
                <td>
                  <button
                    className="btn btn-success btn-sm m-0"
                    onClick={() => updatePatientDetails(patient.id)}
                  >
                    Update
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
        </div>
        </div>
    )
}

export default SearchResult;