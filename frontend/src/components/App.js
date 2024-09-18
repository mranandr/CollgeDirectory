import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./LoginPage";
import Student from "./StudentPage";
import Faculty from "./FacultyPage";
import Admin from "./AdminPage";
import PrivateRoute from "./PrivateRoutePage";


const express = require('express');
const cors = require('cors');
const app = express();

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route
          path="/Student"
          element={
            <PrivateRoute roles={["student"]}>
              <Student />
            </PrivateRoute>
          }
        />
        <Route
          path="./Faculty"
          element={
            <PrivateRoute roles={["faculty"]}>
              <Faculty />
            </PrivateRoute>
          }
        />
        <Route
          path="/Admin"
          element={
            <PrivateRoute roles={["admin"]}>
              <Admin />
            </PrivateRoute>
          }
        />
      </Routes>
    </Router>
  );
  app.use(cors({
    origin: 'http://localhost:3000', 
  }));
  
  app.get('/api/data', (req, res) => {
    res.json({ message: 'This is CORS-enabled.' });
  });
  
  app.listen(8081, () => {
    console.log('Server running on http://localhost:8081');
  });
}

export default App;
