import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { Slide, toast, ToastContainer } from "react-toastify";
import { changePasswordOfEmployee } from "./api/ApiService";
import { useAuth } from "./security/AuthContext";

function ChangePassword() {
  const [currentPassword, setCurrentPassword] = useState("");
  const [newPassword, setNewPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");

  const authContext = useAuth();
  const navigate = useNavigate();

  function handleSubmit(e) {
    e.preventDefault();
    const passwordObjectDto = {
      oldPassword: currentPassword,
      newPassword: newPassword,
      confirmNewPassword: confirmPassword,
    };

    changePasswordOfEmployee(
      authContext.loggedinUserHospitalId,
      authContext.loggedinUserId,
      passwordObjectDto
    )
      .then(() => {
        toast.success(
          "Hi " +
            authContext.loggedinUsername +
            ", your password was changed successfully! Please login again with new password. You will be automatically loggedout in 3 seconds",
          {
            position: toast.POSITION.TOP_CENTER,
            autoClose: 3000, //3 seconds
            hideProgressBar: false,
            closeOnClick: true,
            pauseOnHover: true,
            draggable: true,
            transition: Slide,
            theme: "colored",
          }
        );
        setTimeout(function () {
          navigate("/logout");
        }, 3000);
      })
      .catch((err) =>
        toast.error(err.response.data.message, {
          position: toast.POSITION.TOP_CENTER,
          autoClose: 3000, //3 seconds
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: true,
          draggable: true,
          transition: Slide,
          theme: "colored",
        })
      );
  }

  return (
    <div style={{ minHeight: "78.1vh" }}>
      <ToastContainer></ToastContainer>
      <div
        className="mt-5 pb-4 pt-4 container-fluid shadow bg-light"
        style={{ alignContent: "center", width: "510px", borderRadius: "10px" }}
      >
        <div className="container pt-3 pb-3">
          <h2>
            <strong>{authContext.loggedinUsername} change your password</strong>
          </h2>
        </div>
        <div className="container">
          <form onSubmit={handleSubmit}>
            <table>
              <tbody>
                <tr>
                  <td>
                    <label htmlFor="currentPassword">Current Password: </label>
                  </td>
                  <td>
                    <input
                      type="password"
                      value={currentPassword}
                      onChange={(event) =>
                        setCurrentPassword(event.target.value)
                      }
                      required
                    ></input>
                  </td>
                </tr>
                <tr>
                  <td>
                    <label htmlFor="newPassword">New Password: </label>
                  </td>
                  <td>
                    <input
                      type="password"
                      value={newPassword}
                      onChange={(event) => setNewPassword(event.target.value)}
                      required
                    ></input>
                  </td>
                </tr>
                <tr>
                  <td>
                    <label htmlFor="currentPassword">
                      Confirm New Password:{" "}
                    </label>
                  </td>
                  <td>
                    <input
                      type="password"
                      value={confirmPassword}
                      onChange={(event) =>
                        setConfirmPassword(event.target.value)
                      }
                      required
                    ></input>
                  </td>
                </tr>
              </tbody>
            </table>
            <div className="container p-2">
              <button className="btn btn-sm btn-success">
                Change Password
              </button>
            </div>
          </form>
        </div>
        <div className="container">
          <button
            className="btn btn-sm btn-secondary"
            onClick={() => navigate("/welcome")}
          >
            Back to Hospital
          </button>
        </div>
      </div>
    </div>
  );
}

export default ChangePassword;
