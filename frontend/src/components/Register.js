import React, { useState } from 'react';
import axios from 'axios';
import '../Register.css'; 

const Register = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [role, setRole] = useState('STUDENT');
  const [department, setDepartment] = useState('');
  const [year, setYear] = useState('');
  const [officeHours, setOfficeHours] = useState('');

  const handleRegister = async (e) => {
    e.preventDefault();
  
    const registerData = {
      username,
      password,
      role,
      department,
      year: role === 'STUDENT' ? year : null,
      officeHours: role === 'FACULTY' ? officeHours : null,
    };
  
    try {
      const response = await axios.post('http://localhost:8081/api/users/register', registerData); // Ensure the correct API endpoint
      alert(response.data); // Success message
    } catch (error) {
      if (error.response) {
        // Backend returned a response with an error status
        console.error('Error response:', error.response);
        alert(`Error: ${error.response.data}`);
      } else if (error.request) {
        // Request was made, but no response was received
        console.error('Error request:', error.request);
        alert('No response from the server. Please try again later.');
      } else {
        // Something happened in setting up the request
        console.error('Error:', error.message);
        alert(`Error: ${error.message}`);
      }
    }
  };
  

  return (
    <div className="register-container">
      <form onSubmit={handleRegister} className="register-form">
        <h2>Register</h2>
        <label>Username:</label>
        <input
          type="text"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          required
        />
        <label>Password:</label>
        <input
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          required
        />
        <label>Role:</label>
        <select value={role} onChange={(e) => setRole(e.target.value)}>
          <option value="STUDENT">Student</option>
          <option value="FACULTY">Faculty</option>
        </select>

        <label>Department:</label>
        <input
          type="text"
          value={department}
          onChange={(e) => setDepartment(e.target.value)}
          required
        />

        {role === 'STUDENT' && (
          <>
            <label>Year:</label>
            <input
              type="text"
              value={year}
              onChange={(e) => setYear(e.target.value)}
            />
          </>
        )}

        {role === 'FACULTY' && (
          <>
            <label>Office Hours:</label>
            <input
              type="text"
              value={officeHours}
              onChange={(e) => setOfficeHours(e.target.value)}
            />
          </>
        )}

        <button type="submit">Register</button>
      </form>
    </div>
  );
};

export default Register;
