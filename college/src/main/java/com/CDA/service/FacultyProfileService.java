package com.CDA.service;

import com.CDA.model.FacultyProfile;
import com.CDA.repository.FacultyProfileRepository;
import com.CDA.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyProfileService {

    @Autowired
    private FacultyProfileRepository facultyProfileRepository;

    // Create a new FacultyProfile
    public FacultyProfile addUser(FacultyProfile profile) {
        return facultyProfileRepository.save(profile);
    }

    // Get a FacultyProfile by ID
    public Optional<FacultyProfile> getUser(Long id) {
        Optional<FacultyProfile> profile = facultyProfileRepository.findById(id);
        if (profile.isEmpty()) {
            throw new UserNotFoundException("Faculty Profile not found with ID: " + id);
        }
        return profile;
    }

    // Get all FacultyProfiles
    public List<FacultyProfile> getAllUsers() {
        return facultyProfileRepository.findAll();
    }

    // Update an existing FacultyProfile
    public FacultyProfile updateUser(Long id, FacultyProfile profile) {
        Optional<FacultyProfile> existingProfile = facultyProfileRepository.findById(profile.getUser().getId());
        if (existingProfile.isEmpty()) {
            throw new UserNotFoundException("Faculty Profile not found with ID: " + profile.getUser().getId());
        }
        return facultyProfileRepository.save(profile);
    }

    // Delete a FacultyProfile by ID
    public void deleteProfile(Long id) {
        if (!facultyProfileRepository.existsById(id)) {
            throw new UserNotFoundException("Faculty Profile not found with ID: " + id);
        }
        facultyProfileRepository.deleteById(id);
    }
}
