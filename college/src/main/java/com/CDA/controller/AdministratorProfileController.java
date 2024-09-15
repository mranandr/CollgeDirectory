package com.CDA.controller;

import com.CDA.model.AdministratorProfile;
import com.CDA.service.AdministratorProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/administrator-profiles")
public class AdministratorProfileController {

    @Autowired
    private AdministratorProfileService administratorProfileService;

    // Create new AdministratorProfile
    @PostMapping("/create")
    public ResponseEntity<AdministratorProfile> createProfile(@RequestBody AdministratorProfile profile) {
        return ResponseEntity.ok(administratorProfileService.createProfile(profile));
    }

    // Get AdministratorProfile by ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<AdministratorProfile>> getProfileById(@PathVariable Long id) {
        return ResponseEntity.ok(administratorProfileService.findProfileById(id));
    }

    // Get all AdministratorProfiles
    @GetMapping("/all")
    public ResponseEntity<List<AdministratorProfile>> getAllProfiles() {
        return ResponseEntity.ok(administratorProfileService.findAllProfiles());
    }

    // Update AdministratorProfile
    @PutMapping("/update")
    public ResponseEntity<AdministratorProfile> updateProfile(@RequestBody AdministratorProfile profile) {
        return ResponseEntity.ok(administratorProfileService.updateProfile(profile));
    }

    // Delete AdministratorProfile
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProfile(@PathVariable Long id) {
        administratorProfileService.deleteProfile(id);
        return ResponseEntity.ok("Profile deleted successfully.");
    }
}
