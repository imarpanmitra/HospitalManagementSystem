import React from "react";


function Logout() {
  
  setTimeout(function () {
    window.location.replace("/");
  }, 2000);
  return (
    <div style={{height: 'calc(100vh - 164px)'}} className="mt-5 p-5" >
    <div className="container">
      <hr />
      <h2>
        <strong>You are successfully Logged Out.</strong>
      </h2>
      </div >
      <div className="container">
      <p />
      <h4>Thanks for using our app.</h4>
      <br />
      </div>
      <div className="container">
      <h5>You will be redirected to Log In page</h5>
      </div>
      <hr />
    </div>
    
  );
}

export default Logout;