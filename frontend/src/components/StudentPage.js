import React, { useEffect, useState } from "react";
import axios from "axios";

const Student= () => {
  const [profile, setProfile] = useState({});
  const [searchResults, setSearchResults] = useState([]);
  const [searchQuery, setSearchQuery] = useState("");

  useEffect(() => {
    const fetchProfile = async () => {
      const token = localStorage.getItem("token");
      const response = await axios.get("/api/student/profile", {
        headers: { Authorization: `Bearer ${token}` },
      });
      setProfile(response.data);
    };
    fetchProfile();
  }, []);

  const handleSearch = async () => {
    const token = localStorage.getItem("token");
    const response = await axios.get(`/api/student/search?name=${searchQuery}`, {
      headers: { Authorization: `Bearer ${token}` },
    });
    setSearchResults(response.data);
  };

  return (
    <div className="student-dashboard">
      <h1>Welcome, {profile.name}</h1>
      <div className="profile-section">
        <h3>Personal Information</h3>
        <p>Name: {profile.name}</p>
        <p>Email: {profile.email}</p>
        <p>Department: {profile.department}</p>
        {/* Add more fields as needed */}
      </div>

      <div className="search-section">
        <h3>Search for Other Students</h3>
        <input
          type="text"
          value={searchQuery}
          onChange={(e) => setSearchQuery(e.target.value)}
          placeholder="Search by name"
        />
        <button onClick={handleSearch}>Search</button>
        <ul>
          {searchResults.map((student) => (
            <li key={student.id}>
              {student.name} - {student.department}
            </li>
          ))}
        </ul>
      </div>

      <div className="faculty-section">
        <h3>Your Faculty Advisors</h3>
        <ul>
          {profile.facultyAdvisors &&
            profile.facultyAdvisors.map((advisor) => (
              <li key={advisor.id}>
                {advisor.name} - {advisor.email}
              </li>
            ))}
        </ul>
      </div>
    </div>
  );
};

export default Student;
