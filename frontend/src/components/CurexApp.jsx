import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import AuthProvider, { useAuth } from "./security/AuthContext";
import React from "react";
import Login from "./Login";
import Logout from "./Logout";
import Welcome from "./Welcome";
import Error from "./Error";
import NavigationBar from "./NavigationBar";
import Footer from "./Footer";
import Home from "./Home";
import PatientList from "./PatientList";
import RegistrationForm from "./RegistrationForm";
import AddOrUpdatePatient from "./AddOrUpdatePatient";
import BloodDetailsList from "./BloodDetails";
import SearchBar from "./SearchBar";
import SearchResult from "./SearchResult";
import BloodUnits from "./BloodUnits";
import AddOrUpdateBloodDetails from "./AddOrUpdateBloodDetails";

function AuthenticatedRouteBeforeLogin({ children }) {
  const authContext = useAuth();
  if (authContext.isLoggedin) {
    return children;
  } else {
    return <Login />;
  }
}

function AuthenticatedRouteAfterLogout({ children }) {
  const authContext = useAuth();
  if (authContext.isLoggedin) {
    return children;
  } else {
    return <Logout />;
  }
}

function CurexApp() {
  return (
    <>
      <AuthProvider>
        <Router>
          <NavigationBar />
          
          <Routes>
            <Route exact path="/" element={<Home />} />
            <Route exact path="/login" element={<Login />} />
            <Route exact path="/register" element={<RegistrationForm />} />

            <Route
              exact
              path="/logout"
              element={
                <AuthenticatedRouteAfterLogout>
                  <Logout />
                </AuthenticatedRouteAfterLogout>
              }
            />
            <Route
              exact
              path="/welcome"
              element={
                <AuthenticatedRouteBeforeLogin>
                  <Welcome />
                </AuthenticatedRouteBeforeLogin>
              }
            />
            <Route
              exact
              path="/patients"
              element={
                <AuthenticatedRouteBeforeLogin>
                  <PatientList />
                </AuthenticatedRouteBeforeLogin>
              }
            />
            <Route
              exact
              path="/bloodbank/blooddetails"
              element={
                <AuthenticatedRouteBeforeLogin>
                  <BloodDetailsList />
                </AuthenticatedRouteBeforeLogin>
              }
            />
            <Route
              exact
              path="/search/results"
              element={
                <AuthenticatedRouteBeforeLogin>
                  <SearchResult />
                </AuthenticatedRouteBeforeLogin>
              }
            />
            <Route
              exact
              path="/bloodbank/bloodunits"
              element={
                <AuthenticatedRouteBeforeLogin>
                  <BloodUnits />
                </AuthenticatedRouteBeforeLogin>
              }
            />
            <Route
              exact
              path="/patients/:id"
              element={
                <AuthenticatedRouteBeforeLogin>
                  <AddOrUpdatePatient />
                </AuthenticatedRouteBeforeLogin>
              }
            />

            <Route
              exact
              path="/bloodbank/blooddetails/:id"
              element={
                <AuthenticatedRouteBeforeLogin>
                  <AddOrUpdateBloodDetails />
                </AuthenticatedRouteBeforeLogin>
              }
            />

            <Route path="*" element={<Error />} />
          </Routes>
          <Footer />
        </Router>
      </AuthProvider>
    </>
  );  
}

export default CurexApp;