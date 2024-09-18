package com.CDA.service;

import com.CDA.model.StudentProfile;
import com.CDA.repository.StudentProfileRepository;
import com.CDA.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentProfileService {

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    // Create a new StudentProfile
    public StudentProfile addUser(StudentProfile profile) {
        return studentProfileRepository.save(profile);
    }

    // Get a StudentProfile by ID
    public Optional<StudentProfile> getUser(Long id) {
        Optional<StudentProfile> profile = studentProfileRepository.findById(id);
        if (profile.isEmpty()) {
            throw new UserNotFoundException("Student Profile not found with ID: " + id);
        }
        return profile;
    }

    // Get all StudentProfiles
    public List<StudentProfile> getAllUsers() {
        return studentProfileRepository.findAll();
    }

    // Update an existing StudentProfile
    public StudentProfile updateUser(Long id, StudentProfile profile) {
        Optional<StudentProfile> existingProfile = studentProfileRepository.findById(profile.getId());
        if (existingProfile.isEmpty()) {
            throw new UserNotFoundException("Student Profile not found with ID: " + profile.getId());
        }
        return studentProfileRepository.save(profile);
    }

    // Delete a StudentProfile by ID
    public void deleteProfile(Long id) {
        if (!studentProfileRepository.existsById(id)) {
            throw new UserNotFoundException("Student Profile not found with ID: " + id);
        }
        studentProfileRepository.deleteById(id);
    }
}
