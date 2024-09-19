import React, { useState } from 'react';
import axios from 'axios';
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import '../Register.css'; 

const Register = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [role, setRole] = useState('STUDENT');
  const [department, setDepartment] = useState('');
  const [year, setYear] = useState('');
  const [officeHours, setOfficeHours] = useState('');

  // Handle form submission
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
      var url = 'http://localhost:8081/api/users/register';
      const response = await axios.post(url, registerData); 
      toast.success("Registration successful!", { autoClose: 3000 });
    } catch (error) {
      if (error.response) {
        toast.error(`Error: ${error.response.data}`, { autoClose: 3000 });
      } else if (error.request) {
        toast.error('No response from the server. Please try again later.', { autoClose: 3000 });
      } else {
        toast.error(`Error: ${error.message}`, { autoClose: 3000 });
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

      {/* Toast container for displaying toast notifications */}
      <ToastContainer />
    </div>
  );
};

export default Register;
