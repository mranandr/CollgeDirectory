import React from "react";
import { Link, useNavigate } from "react-router-dom";

const Navbar = () => {
  const role = localStorage.getItem("role");
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("role");
    navigate("/login");
  };

  return (
    <nav className="navbar">
      <h2>College Directory</h2>
      <ul>
        {role === "student" && (
          <>
            <li>
              <Link to="/student">Student Dashboard</Link>
            </li>
            <li>
              <Link to="/search-students">Search for Students</Link>
            </li>
            <li>
              <Link to="/contact-advisors">Contact Advisors</Link>
            </li>
          </>
        )}
        {role === "faculty" && (
          <>
            <li>
              <Link to="/faculty">Faculty Dashboard</Link>
            </li>
            <li>
              <Link to="/manage-classes">Manage Classes</Link>
            </li>
            <li>
              <Link to="/update-profile">Update Profile</Link>
            </li>
          </>
        )}
        {role === "admin" && (
          <>
            <li>
              <Link to="/admin">Admin Dashboard</Link>
            </li>
            <li>
              <Link to="/manage-students">Manage Students</Link>
            </li>
            <li>
              <Link to="/manage-faculty">Manage Faculty</Link>
            </li>
          </>
        )}
        <li>
          <button onClick={handleLogout}>Logout</button>
        </li>
      </ul>
    </nav>
  );
};

export default Navbar;
