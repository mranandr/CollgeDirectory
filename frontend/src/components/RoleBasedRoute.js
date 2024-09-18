import React from 'react';
import { Route, Redirect } from 'react-router-dom';

const RoleBasedRoute = ({ component: Component, allowedRoles, userRole, ...rest }) => (
  <Route
    {...rest}
    render={props =>
      allowedRoles.includes(userRole) ? (
        <Component {...props} />
      ) : (
        <Redirect to="/unauthorized" />
      )
    }
  />
);

export default RoleBasedRoute;
