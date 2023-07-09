import { createContext, useContext, useState } from "react";
import { retrieveSpecificHospitalById } from "../api/ApiService";

export const AuthContext = createContext();

export const useAuth = () => useContext(AuthContext);

function Authprovider({ children }) {
  const [isLoggedin, setIsLoggedin] = useState(false);
  const [loggedinUsername, setLoggedinUsername] = useState(null);
  const [loggedinUserId, setLoggedinUserId] = useState();
  const [invalidCredentialsMessage, setInvalidCredentialsMessage] =
    useState(false);

  const [loggedinUserHospitalId, setLoggedinUserHospitalId] = useState();

  const [loggedinUserHospitalName, setLoggedinUserHospitalName] = useState("");

  const [searchResults, setSearchResults] = useState([]);

  const [availableBed, setAvailableBed] = useState(0);

  //const [token, setToken] = useState(null);
  const valueToBeShared = {
    isLoggedin,
    setIsLoggedin,
    loggedinUsername,
    setLoggedinUsername,

    invalidCredentialsMessage,
    setInvalidCredentialsMessage,

    loggedinUserHospitalId,
    setLoggedinUserHospitalId,

    loggedinUserHospitalName,
    setLoggedinUserHospitalName,

    searchResults,
    setSearchResults,

    availableBed,
    setAvailableBed,

    loggedinUserId,
    setLoggedinUserId,
  };

  return (
    <AuthContext.Provider value={valueToBeShared}>
      {children}
    </AuthContext.Provider>
  );
}

export default Authprovider;
