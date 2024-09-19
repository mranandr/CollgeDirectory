import React, { useState, useEffect } from 'react';
import axios from 'axios';

const StudentDashboard = () => {
  const [studentProfile, setStudentProfile] = useState(null);
  
  // Fetch the username and token from local storage
  const username = localStorage.getItem('username');
  const token = localStorage.getItem('auth_token');

  useEffect(() => {
    const fetchStudentProfile = async () => {
      try {
        // Make the API call using the username
        const response = await axios.get(`http://localhost:8081/api/users/${username}`, {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });
        setStudentProfile(response.data);
      } catch (error) {
        console.error('Error fetching student profile', error);
      }
    };

    if (username) {
      fetchStudentProfile();
    }
  }, [username, token]); // Re-fetch when username or token changes

  if (!studentProfile) {
    return <div>Loading...</div>;
  }

  return (
    <div>
      <h2>Student Profile</h2>
      <p>Name: {studentProfile.name}</p>
      <p>Username: {studentProfile.username}</p>
      <p>Year: {studentProfile.year}</p>
      <p>Department: {studentProfile.department}</p>
      <img src={studentProfile.photo} alt="Profile" />
    </div>
  );
};

export default StudentDashboard;
