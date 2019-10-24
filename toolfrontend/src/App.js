import React from 'react';
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";

import Home from './components/home';
import Project from './components/project';

function App() {
  return (
    <Router>

      <nav className="navbar navbar-expand-lg navbar-dark bg-dark ">
        <div className="container">
          {/* <a className="navbar-brand" href="/"></a> */}
          <button className="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span className="navbar-toggler-icon"></span>
          </button>
          <div className="collapse navbar-collapse" id="navbarResponsive">



            <ul className="navbar-nav ml-auto">
              <li className="nav-item">
                <Link to="/projects" className="nav-link"><b>Projects</b></Link>
              </li>
              <li className="nav-item">
                <Link to="/" className="nav-link"><b>Upload</b></Link>
              </li>

            </ul>

          </div>
        </div>
      </nav>

      <Route path="/" exact component={Home} />
      <Route path="/projects" component={Project} />


    </Router>
  );
}

export default App;
