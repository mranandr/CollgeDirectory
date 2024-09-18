import React from 'react';
import { Navigate } from 'react-router-dom';

const PrivateRoute = ({ children, roles }) => {
  // Assuming you have some logic to determine if a user is authenticated and their role
  const isAuthenticated = true; // Replace with actual authentication check
  const userRole = 'student'; // Replace with actual role check

  if (!isAuthenticated) {
    return <Navigate to="/login" />;
  }

  if (!roles.includes(userRole)) {
    return <Navigate to="/login" />;
  }

  return children;
};

export default PrivateRoute;
