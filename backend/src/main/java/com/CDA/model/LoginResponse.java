package com.CDA.model;

public class LoginResponse {

    private String authToken;
    private String userRole;

    // Constructor
    public LoginResponse(String authToken, String userRole) {
        this.authToken = authToken;
        this.userRole = userRole;
    }

    // Getters and setters
    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
