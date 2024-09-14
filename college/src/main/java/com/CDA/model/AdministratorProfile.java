package com.CDA.model;

import jakarta.persistence.*;

@Entity
@Table(name = "administrator_profile")
public class AdministratorProfile {

    @Id
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String photo;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    // Getters and Setters
}