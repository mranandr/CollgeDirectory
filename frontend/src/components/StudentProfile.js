import React, { useState, useEffect } from 'react';
import axios from 'axios';

const StudentProfile = () => {
  const [studentProfile, setStudentProfile] = useState(null);
  const token = localStorage.getItem('token');

  useEffect(() => {
    const fetchStudentProfile = async () => {
      try {
        const response = await axios.get('/api/student/profile', {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        });
        setStudentProfile(response.data);
      } catch (error) {
        console.error('Error fetching student profile', error);
      }
    };

    fetchStudentProfile();
  }, [token]);

  if (!studentProfile) {
    return <div>Loading...</div>;
  }

  return (
    <div>
      <h2>Student Profile</h2>
      <p>Name: {studentProfile.name}</p>
      <p>Year: {studentProfile.year}</p>
      <p>Department: {studentProfile.department}</p>
      <img src={studentProfile.photo} alt="Profile" />
    </div>
  );
};

export default StudentProfile;
