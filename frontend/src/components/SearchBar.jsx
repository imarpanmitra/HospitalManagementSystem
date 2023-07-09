
import { useState } from "react";
import { Button } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import { retrieveAllPatients } from "./api/ApiService";

import { useAuth } from "./security/AuthContext";



function SearchBar() {
const [searchInput, setSearchInput] = useState("");
const authContext = useAuth();
const navigate = useNavigate();
const [patients, setPatients] = useState([]);

const handleChange = (e) => {
    e.preventDefault();
    retrieveAllPatients(authContext.loggedinUserHospitalId)
    .then((res) => {
    //   console.log(res.data);
    //   console.log(authContext.loggedinUserHospitalId);
      setPatients(res.data);
    })
    .catch((err) => {
      console.log(err);
    });
    
    setSearchInput(e.target.value);
    
    let filteredPatients = (patients).filter((patient)=>{
       
            if(patient.patientId === searchInput) {
                return patient;
                
            }
        }
    )
    authContext.setSearchResults(filteredPatients);
  };

  
  
    return (

        <div className="container-xs" >
            <div className="d-flex">
                <input
                type="search"
                placeholder="Type here.."
                className="me-2"
                aria-label="Search"
                value={searchInput}
                onChange={handleChange}
                />
                <Button variant="dark btn-sm" disabled={searchInput.length === 0} onClick={() => navigate("/search/results")}
                    style={{"height": "34px", "marginTop": "6px"}}>Search</Button>
            </div>
        </div>
    )
}

export default SearchBar;