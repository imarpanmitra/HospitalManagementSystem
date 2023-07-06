import { Link } from "react-router-dom";
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import { NavItem } from "react-bootstrap";
import { useAuth } from "./security/AuthContext";

function NavigationBar() {
  const authContext = useAuth();

  function handleHomeButton() {
    if (
      authContext.isLoggedin === true &&
      authContext.invalidCredentialsMessage === false
    )
      return (
        <NavItem>
          <Link to="/welcome" className="nav-link">
            Home
          </Link>
        </NavItem>
      );
  }
  function handleLoginLogoutButton() {
    console.log(authContext.isLoggedin);
    if (authContext.isLoggedin)
      return (
        <NavItem>
          <Link to="/logout" className="nav-link">
            Logout
          </Link>
        </NavItem>
      );
    else {
      return (
        <NavItem>
          <Link to="/login" className="nav-link">
            Login
          </Link>
        </NavItem>
      );
    }
  }
  return (
    <Navbar
      bg="light shadow-sm "
      expand="xl"
      className="p-2 mb-3"
      style={{ display: "block" }}
    >
      <Container>
        <Navbar.Brand>
          <NavItem>
            <Link to="/" className="nav-link">
              <strong>CureX</strong>
            </Link>
          </NavItem>
        </Navbar.Brand>
        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          {handleHomeButton()}
        </Navbar.Collapse>
        <Nav className="justify-content-end">{handleLoginLogoutButton()}</Nav>
      </Container>
    </Navbar>
  );
}

export default NavigationBar;
