package com.CDA.service;

import com.CDA.model.FacultyProfile;
import com.CDA.repository.FacultyProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyProfileService {

    private final FacultyProfileRepository facultyProfileRepository;

    @Autowired
    public FacultyProfileService(FacultyProfileRepository facultyProfileRepository) {
        this.facultyProfileRepository = facultyProfileRepository;
    }

    public FacultyProfile createProfile(FacultyProfile profile) {
        return facultyProfileRepository.save(profile);
    }

    public Optional<FacultyProfile> findProfileById(Long id) {
        return facultyProfileRepository.findById(id);
    }

    public FacultyProfile updateProfile(FacultyProfile profile) {
        return facultyProfileRepository.save(profile);
    }

    public void deleteProfile(Long id) {
        facultyProfileRepository.deleteById(id);
    }

    public List<FacultyProfile> findAllProfiles() {
        return facultyProfileRepository.findAll();
    }
}
