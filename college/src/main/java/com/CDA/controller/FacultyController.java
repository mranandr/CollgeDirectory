package com.CDA.controller;

import com.CDA.model.FacultyProfile;
import com.CDA.service.FacultyProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/faculty")
public class FacultyController {

    @Autowired
    private FacultyProfileService facultyProfileService;

    @PostMapping("/create")
    @Operation(summary = "Create a new faculty profile")
    public ResponseEntity<FacultyProfile> createFaculty(@RequestBody FacultyProfile profile) {
        return ResponseEntity.ok(facultyProfileService.addUser(profile));
    }

    @GetMapping("/{id}")
    @Operation(summary = "get faculty by id")
    public ResponseEntity<Optional<FacultyProfile>> getFacultyById(@PathVariable Long id) {
        return ResponseEntity.ok(facultyProfileService.getUser(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<FacultyProfile>> getAllFaculties() {
        return ResponseEntity.ok(facultyProfileService.getAllUsers());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFaculty(@PathVariable Long id) {
        facultyProfileService.deleteProfile(id);
        return ResponseEntity.ok("Faculty profile deleted successfully.");
    }
}

