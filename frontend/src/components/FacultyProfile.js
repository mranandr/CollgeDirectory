import React, { useState, useEffect } from 'react';
import axios from 'axios';

const FacultyProfile = () => {
  const [facultyProfile, setFacultyProfile] = useState(null);
  const token = localStorage.getItem('token');

  useEffect(() => {
    const fetchFacultyProfile = async () => {
      try {
        const response = await axios.get('/api/faculty/profile', {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });
        setFacultyProfile(response.data);
      } catch (error) {
        console.error('Error fetching faculty profile', error);
      }
    };

    fetchFacultyProfile();
  }, [token]);

  if (!facultyProfile) {
    return <div>Loading...</div>;
  }

  return (
    <div>
      <h2>Faculty Profile</h2>
      <p>Name: {facultyProfile.name}</p>
      <p>Department: {facultyProfile.department}</p>
      <p>Office Hours: {facultyProfile.officeHours}</p>
      <img src={facultyProfile.photo} alt="Profile" />
    </div>
  );
};

export default FacultyProfile;
