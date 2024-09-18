package com.CDA.controller;

import com.CDA.model.StudentProfile;
import com.CDA.model.FacultyProfile;
import com.CDA.service.AdministratorProfileService;
import com.CDA.service.StudentProfileService;
import com.CDA.service.FacultyProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class AdministratorProfileController {

    @Autowired
    private StudentProfileService studentProfileService;

    @Autowired
    private FacultyProfileService facultyProfileService;

    @Autowired
    private AdministratorProfileService adminService;

    // Create new StudentProfile
    @PostMapping("/student/create")
    public ResponseEntity<StudentProfile> createStudent(@RequestBody StudentProfile profile) {
        StudentProfile createdProfile = studentProfileService.addUser(profile);
        return ResponseEntity.ok(createdProfile);
    }

    // Create new FacultyProfile
    @PostMapping("/faculty/create")
    public ResponseEntity<FacultyProfile> createFaculty(@RequestBody FacultyProfile profile) {
        FacultyProfile createdProfile = facultyProfileService.addUser(profile);
        return ResponseEntity.ok(createdProfile);
    }

    // Get StudentProfile by ID
    @GetMapping("/student/{id}")
    public ResponseEntity<Optional<StudentProfile>> getStudentById(@PathVariable Long id) {
        Optional<StudentProfile> profile = studentProfileService.getUser(id);
        return ResponseEntity.ok(profile);
    }

    // Get FacultyProfile by ID
    @GetMapping("/faculty/{id}")
    public ResponseEntity<Optional<FacultyProfile>> getFacultyById(@PathVariable Long id) {
        Optional<FacultyProfile> profile = facultyProfileService.getUser(id);
        return ResponseEntity.ok(profile);
    }

    // Get all StudentProfiles
    @GetMapping("/students")
    public ResponseEntity<List<StudentProfile>> getAllStudents() {
        List<StudentProfile> profiles = studentProfileService.getAllUsers();
        return ResponseEntity.ok(profiles);
    }

    // Get all FacultyProfiles
    @GetMapping("/faculties")
    public ResponseEntity<List<FacultyProfile>> getAllFaculties() {
        List<FacultyProfile> profiles = facultyProfileService.getAllUsers();
        return ResponseEntity.ok(profiles);
    }

    // Update StudentProfile
    @PutMapping("/student/update/{id}")
    public ResponseEntity<StudentProfile> updateStudent(@PathVariable Long id, @RequestBody StudentProfile profile) {
        StudentProfile updatedProfile = studentProfileService.updateUser(id, profile);
        return ResponseEntity.ok(updatedProfile);
    }

    // Update FacultyProfile
    @PutMapping("/faculty/update/{id}")
    public ResponseEntity<FacultyProfile> updateFaculty(@PathVariable Long id, @RequestBody FacultyProfile profile) {
        FacultyProfile updatedProfile = facultyProfileService.updateUser(id, profile);
        return ResponseEntity.ok(updatedProfile);
    }

    // Delete StudentProfile by ID
    @DeleteMapping("/student/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        studentProfileService.deleteProfile(id);
        return ResponseEntity.ok("Student profile deleted successfully.");
    }

    // Delete FacultyProfile by ID
    @DeleteMapping("/faculty/delete/{id}")
    public ResponseEntity<String> deleteFaculty(@PathVariable Long id) {
        facultyProfileService.deleteProfile(id);
        return ResponseEntity.ok("Faculty profile deleted successfully.");
    }

    // Dashboard with Graphs (Placeholder, you can add more specifics)
    @GetMapping("/dashboard")
    public ResponseEntity<String> getAdminDashboard() {
        String dashboardData = adminService.getDashboardData();
        return ResponseEntity.ok(dashboardData);
    }
}
