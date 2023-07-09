import { Link } from "react-router-dom";

function Home() {
  return (
    <div  style={{height: 'calc(100vh - 133px)'}} className="pt-5">
        <div>
          <div className="container mt-5">
            <h2>
              <strong>Welcome to CureX</strong>
            </h2>
          </div>
        <div className="container">
          <h4>
            <p>
              Please <Link to="/login">login</Link> to use our app
            </p>
          </h4>
        </div>
      </div>
    </div>
  );
}

export default Home;