package com.CDA.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotBlank
    @Size(max = 50)
    private String username;

    @Column(nullable = false)
    @NotBlank
    @Size(max = 255)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;  // Removed @NotBlank

    @Column(nullable = false)
    @NotBlank
    @Size(max = 100)
    private String name;

    @Column(unique = true, nullable = false)
    @NotBlank
    @Size(max = 100)
    private String email;

    @Size(max = 15)
    private String phone;

    public User(String username, Long id, String password, Role role, String name, String email, String phone) {
        this.username = username;
        this.id = id;
        this.password = password;
        this.role = role;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank @Size(max = 50) String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank @Size(max = 50) String username) {
        this.username = username;
    }

    public @NotBlank @Size(max = 255) String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank @Size(max = 255) String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public @NotBlank @Size(max = 100) String getName() {
        return name;
    }

    public void setName(@NotBlank @Size(max = 100) String name) {
        this.name = name;
    }

    public @NotBlank @Size(max = 100) String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank @Size(max = 100) String email) {
        this.email = email;
    }

    public @Size(max = 15) String getPhone() {
        return phone;
    }

    public void setPhone(@Size(max = 15) String phone) {
        this.phone = phone;
    }

    public enum Role {
        STUDENT,
        FACULTY_MEMBER,
        ADMINISTRATOR
    }
}
