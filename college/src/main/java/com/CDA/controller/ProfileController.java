//package com.CDA.controller;
//
//import com.CDA.model.AdministratorProfile;
//import com.CDA.model.FacultyProfile;
//import com.CDA.model.StudentProfile;
//import com.CDA.service.ProfileService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/profiles")
//public class ProfileController {
//
//    @Autowired
//    private ProfileService profileService;
//
//    // StudentProfile endpoints
//    @PostMapping("/student")
//    public ResponseEntity<StudentProfile> createStudentProfile(@RequestBody StudentProfile profile) {
//        return ResponseEntity.ok(profileService.createStudentProfile(profile));
//    }
//
//    @PutMapping("/student/{id}")
//    public ResponseEntity<StudentProfile> updateStudentProfile(@PathVariable Long id, @RequestBody StudentProfile profile) {
//        return ResponseEntity.ok(profileService.updateStudentProfile(id, profile));
//    }
//
//    @GetMapping("/student/{id}")
//    public ResponseEntity<StudentProfile> getStudentProfile(@PathVariable Long id) {
//        StudentProfile profile = profileService.getStudentProfile(id);
//        return ResponseEntity.ok(profile);
//    }
//
//    @DeleteMapping("/student/{id}")
//    public ResponseEntity<Void> deleteStudentProfile(@PathVariable Long id) {
//        profileService.deleteStudentProfile(id);
//        return ResponseEntity.noContent().build();
//    }
//
//    // FacultyProfile endpoints
//    @PostMapping("/faculty")
//    public ResponseEntity<FacultyProfile> createFacultyProfile(@RequestBody FacultyProfile profile) {
//        return ResponseEntity.ok(profileService.createFacultyProfile(profile));
//    }
//
//    @PutMapping("/faculty/{id}")
//    public ResponseEntity<FacultyProfile> updateFacultyProfile(@PathVariable Long id, @RequestBody FacultyProfile profile) {
//        return ResponseEntity.ok(profileService.updateFacultyProfile(id, profile));
//    }
//
//    @GetMapping("/faculty/{id}")
//    public ResponseEntity<FacultyProfile> getFacultyProfile(@PathVariable Long id) {
//        FacultyProfile profile = profileService.getFacultyProfile(id);
//        return ResponseEntity.ok(profile);
//    }
//
//    @DeleteMapping("/faculty/{id}")
//    public ResponseEntity<Void> deleteFacultyProfile(@PathVariable Long id) {
//        profileService.deleteFacultyProfile(id);
//        return ResponseEntity.noContent().build();
//    }
//
//    // AdministratorProfile endpoints
//    @PostMapping("/administrator")
//    public ResponseEntity<AdministratorProfile> createAdministratorProfile(@RequestBody AdministratorProfile profile) {
//        return ResponseEntity.ok(profileService.createAdministratorProfile(profile));
//    }
//
//    @PutMapping("/administrator/{id}")
//    public ResponseEntity<AdministratorProfile> updateAdministratorProfile(@PathVariable Long id, @RequestBody AdministratorProfile profile) {
//        return ResponseEntity.ok(profileService.updateAdministratorProfile(id, profile));
//    }
//
//    @GetMapping("/administrator/{id}")
//    public ResponseEntity<AdministratorProfile> getAdministratorProfile(@PathVariable Long id) {
//        AdministratorProfile profile = profileService.getAdministratorProfile(id);
//        return ResponseEntity.ok(profile);
//    }
//
//    @DeleteMapping("/administrator/{id}")
//    public ResponseEntity<Void> deleteAdministratorProfile(@PathVariable Long id) {
//        profileService.deleteAdministratorProfile(id);
//        return ResponseEntity.noContent().build();
//    }
//}