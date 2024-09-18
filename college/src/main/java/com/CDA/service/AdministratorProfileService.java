package com.CDA.service;

import com.CDA.model.AdministratorProfile;
import com.CDA.repository.AdministratorProfileRepository;
import com.CDA.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministratorProfileService {

    @Autowired
    private AdministratorProfileRepository administratorProfileRepository;

    // Create a new administrator profile
    public AdministratorProfile addUser(AdministratorProfile profile) {
        return administratorProfileRepository.save(profile);
    }

    // Get an administrator profile by ID
    public Optional<AdministratorProfile> getUser(Long id) {
        Optional<AdministratorProfile> profile = administratorProfileRepository.findById(id);
        if (profile.isEmpty()) {
            throw new UserNotFoundException("Administrator Profile not found with ID: " + id);
        }
        return profile;
    }

    // Get all administrator profiles
    public List<AdministratorProfile> getAllUsers() {
        return administratorProfileRepository.findAll();
    }

    // Update an existing administrator profile
    public AdministratorProfile updateUser(AdministratorProfile profile) {
        Optional<AdministratorProfile> existingProfile = administratorProfileRepository.findById(profile.getUser().getId());
        if (existingProfile.isEmpty()) {
            throw new UserNotFoundException("Administrator Profile not found with ID: " + profile.getUser().getId());
        }
        return administratorProfileRepository.save(profile);
    }

    // Delete an administrator profile by ID
    public void deleteProfile(Long id) {
        if (!administratorProfileRepository.existsById(id)) {
            throw new UserNotFoundException("Administrator Profile not found with ID: " + id);
        }
        administratorProfileRepository.deleteById(id);
    }

    // Dashboard data generation (stub for future implementation)
    public String getDashboardData() {
        // This could include any aggregation logic for graphs and dashboards
        return "Dashboard Data Placeholder";
    }
}
