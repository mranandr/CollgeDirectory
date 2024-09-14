package com.CDA.model;

import jakarta.persistence.*;

@Entity
@Table(name = "faculty_profile")
public class FacultyProfile {

    @Id
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String photo;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    private String officeHours;

    // Getters and Setters
}