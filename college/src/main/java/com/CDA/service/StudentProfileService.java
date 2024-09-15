package com.CDA.service;

import com.CDA.model.StudentProfile;
import com.CDA.model.User;
import com.CDA.repository.StudentProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentProfileService {

    private final StudentProfileRepository studentProfileRepository;

    @Autowired
    public StudentProfileService(StudentProfileRepository studentProfileRepository) {
        this.studentProfileRepository = studentProfileRepository;
    }

    public StudentProfile createProfile(StudentProfile profile) {
        return studentProfileRepository.save(profile);
    }

    public Optional<StudentProfile> findProfileById(Long id) {
        return studentProfileRepository.findById(id);
    }

    public StudentProfile updateProfile(StudentProfile profile) {
        return studentProfileRepository.save(profile);
    }

    public void deleteProfile(Long id) {
        studentProfileRepository.deleteById(id);
    }

    public List<StudentProfile> findAllProfiles() {
        return studentProfileRepository.findAll();
    }
}


