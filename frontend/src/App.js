import React, { useState } from 'react'; // Added useState here
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Login from './components/Login';
import StudentDashboard from './components/StudentDashboard';
import FacultyDashboard from './components/FacultyDashboard';
import AdminDashboard from './components/AdminDashboard';

function App() {
    const [setUserRole] = useState(null); 

    return (
        <Router>
            <Routes>
                <Route path="/" element={<Login setUserRole={setUserRole} />} />
                <Route path="/student-dashboard" element={<StudentDashboard />} />
                <Route path="/faculty-dashboard" element={<FacultyDashboard />} />
                <Route path="/admin-dashboard" element={<AdminDashboard />} />
            </Routes>
        </Router>
    );
}

export default App;
