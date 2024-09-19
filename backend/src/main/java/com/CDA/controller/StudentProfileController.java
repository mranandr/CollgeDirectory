package com.CDA.controller;

import com.CDA.exception.UserNotFoundException;
import com.CDA.model.StudentProfile;
import com.CDA.service.StudentProfileService;
import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/api/student-profiles")
public class StudentProfileController {

    private final StudentProfileService studentProfileService;
    private org.springframework.http.ResponseEntity<StudentProfile> ResponseEntity;

    @Autowired
    public StudentProfileController(StudentProfileService studentProfileService) {
        this.studentProfileService = studentProfileService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<StudentProfile> getStudentProfileByUsername(@PathVariable String username) {
        try {
            Optional<StudentProfile> profile = studentProfileService.findProfileByUsername(username);
            return profile.map(studentProfile -> new ResponseEntity<>(studentProfile, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (UserNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping
    public ResponseEntity<List<StudentProfile>> getAllStudentProfiles() {
        List<StudentProfile> profiles = studentProfileService.findAllProfiles();
        return new ResponseEntity<>(profiles, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StudentProfile> createStudentProfile(@Valid @RequestBody StudentProfile studentProfile) {
        try {
            StudentProfile createdProfile = studentProfileService.updateProfile(null, studentProfile); // Use `null` for creation
            return new ResponseEntity<>(createdProfile, HttpStatus.CREATED);
        } catch (ValidationException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentProfile> updateStudentProfile(
            @PathVariable Long id,
            @Valid @RequestBody StudentProfile studentProfile) {
        try {
            StudentProfile updatedProfile = studentProfileService.updateProfile(id, studentProfile);
            return new ResponseEntity<>(updatedProfile, HttpStatus.OK);
        } catch (UserNotFoundException | ValidationException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudentProfile(@PathVariable Long id) {
        try {
            studentProfileService.deleteProfile(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (UserNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
