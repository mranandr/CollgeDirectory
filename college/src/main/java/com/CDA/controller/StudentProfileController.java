package com.CDA.controller;

import com.CDA.model.StudentProfile;
import com.CDA.service.StudentProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/student")
public class StudentProfileController {

    @Autowired
    private StudentProfileService studentProfileService;

    @GetMapping("/{id}")
    public ResponseEntity<StudentProfile> getStudentProfile(@PathVariable Long id) {
        Optional<StudentProfile> profile = studentProfileService.getUser(id);
        return profile.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}

