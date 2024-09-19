import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Login from './components/Login';
import StudentDashboard from './components/StudentDashboard';
import FacultyDashboard from './components/FacultyDashboard';
import AdminDashboard from './components/AdminDashboard';
// import HomePage from './components/HomePage'; // Optional: For the home page or landing page

function App() {
    return (
        <Router>
            <Routes>
                {/* <Route path="/" element={<HomePage />} /> */}
                <Route path="/login" element={<Login />} />
                <Route path="/student-dashboard" element={<StudentDashboard />} />
                <Route path="/faculty-dashboard" element={<FacultyDashboard />} />
                <Route path="/admin-dashboard" element={<AdminDashboard />} />
                {/* Add other routes here */}
            </Routes>
        </Router>
    );
}

export default App;
