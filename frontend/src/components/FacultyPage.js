import React, { useEffect, useState } from "react";
import axios from "axios";

const Faculty = () => {
  const [students, setStudents] = useState([]);

  useEffect(() => {
    const fetchStudents = async () => {
      const token = localStorage.getItem("token");
      const response = await axios.get("/api/faculty/students", {
        headers: { Authorization: `Bearer ${token}` },
      });
      setStudents(response.data);
    };
    fetchStudents();
  }, []);

  return (
    <div className="faculty">
      <h1>Class List</h1>
      <ul>
        {students.map((student) => (
          <li key={student.id}>
            {student.name} - {student.email}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default Faculty;
