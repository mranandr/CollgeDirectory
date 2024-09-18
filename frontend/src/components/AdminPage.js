import React, { useState, useEffect } from "react";
import axios from "axios";

const Admin = () => {
  const [students, setStudents] = useState([]);
  const [newStudent, setNewStudent] = useState({ name: "", email: "" });

  useEffect(() => {
    const fetchStudents = async () => {
      const token = localStorage.getItem("token");
      const response = await axios.get("/api/admin/students", {
        headers: { Authorization: `Bearer ${token}` },
      });
      setStudents(response.data);
    };
    fetchStudents();
  }, []);

  const handleAddStudent = async () => {
    const token = localStorage.getItem("token");
    await axios.post("/api/admin/add-student", newStudent, {
      headers: { Authorization: `Bearer ${token}` },
    });
  
  };

  return (
    <div className="admin">
      <h1>Manage Students</h1>
      <ul>
        {students.map((student) => (
          <li key={student.id}>
            {student.name} - {student.email}
          </li>
        ))}
      </ul>

      <h3>Add New Student</h3>
      <input
        type="text"
        placeholder="Name"
        value={newStudent.name}
        onChange={(e) => setNewStudent({ ...newStudent, name: e.target.value })}
      />
      <input
        type="email"
        placeholder="Email"
        value={newStudent.email}
        onChange={(e) => setNewStudent({ ...newStudent, email: e.target.value })}
      />
      <button onClick={handleAddStudent}>Add Student</button>
    </div>
  );
};

export default Admin;
