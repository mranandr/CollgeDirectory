//package com.CDA.controller;
//
//import com.CDA.exception.UserNotFoundException;
//import com.CDA.model.StudentProfile;
//import com.CDA.model.User;
//import com.CDA.repository.StudentProfileRepository;
//import com.CDA.repository.UserRepository;
//import com.CDA.service.StudentProfileService;
//import jakarta.validation.ValidationException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Service
//public class StudentProfileService {
//
//    private final StudentProfileRepository studentProfileRepository;
//    private final UserRepository userRepository;
//
//    @Autowired
//    public StudentProfileService(StudentProfileRepository studentProfileRepository, UserRepository userRepository) {
//        this.studentProfileRepository = studentProfileRepository;
//        this.userRepository = userRepository;
//    }
//
//    // Register or Create a StudentProfile
//    public StudentProfile createStudentProfile(Long userId, StudentProfile studentProfile) {
//        // Validate if user exists
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new UserNotFoundException("User with id " + userId + " not found"));
//
//        // Validation for studentProfile details
//        validateStudentProfile(studentProfile);
//
//        studentProfile.setUser(user);  // Associate the profile with the user
//        return studentProfileRepository.save(studentProfile);
//    }
//
//    // Update StudentProfile
//    public StudentProfile updateStudentProfile(Long id, StudentProfile updatedProfile) {
//        // Find the existing profile
//        StudentProfile existingProfile = studentProfileRepository.findById(id)
//                .orElseThrow(() -> new UserNotFoundException("Student profile with id " + id + " not found"));
//
//        // Validate the updated profile details
//        validateStudentProfile(updatedProfile);
//
//        // Set new values to the existing profile
//        existingProfile.setPhoto(updatedProfile.getPhoto());
//        existingProfile.setDepartment(updatedProfile.getDepartment());
//        existingProfile.setYear(updatedProfile.getYear());
//
//        return studentProfileRepository.save(existingProfile);
//    }
//
//    // Find by ID
//    public StudentProfile findStudentProfileById(Long id) {
//        return studentProfileRepository.findById(id)
//                .orElseThrow(() -> new UserNotFoundException("Student profile with id " + id + " not found"));
//    }
//
//    // Delete StudentProfile
//    public void deleteStudentProfile(Long id) {
//        if (!studentProfileRepository.existsById(id)) {
//            throw new UserNotFoundException("Student profile with id " + id + " not found");
//        }
//        studentProfileRepository.deleteById(id);
//    }
//
//    // Find All Student Profiles
//    public List<StudentProfile> findAllStudentProfiles() {
//        return studentProfileRepository.findAll();
//    }
//
//    // Custom validation for the StudentProfile
//    private void validateStudentProfile(StudentProfile studentProfile) {
//        if (studentProfile.getPhoto() == null || studentProfile.getPhoto().isEmpty()) {
//            throw new ValidationException("Photo cannot be null or empty");
//        }
//
//        if (studentProfile.getDepartment() == null) {
//            throw new ValidationException("Department must be provided");
//        }
//
//        if (studentProfile.getYear() == null || studentProfile.getYear().isEmpty()) {
//            throw new ValidationException("Year cannot be null or empty");
//        }
//    }
//}