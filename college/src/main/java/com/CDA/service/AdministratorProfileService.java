package com.CDA.service;

import com.CDA.model.AdministratorProfile;
import com.CDA.repository.AdministratorProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministratorProfileService {

    private final AdministratorProfileRepository administratorProfileRepository;

    @Autowired
    public AdministratorProfileService(AdministratorProfileRepository administratorProfileRepository) {
        this.administratorProfileRepository = administratorProfileRepository;
    }

    public AdministratorProfile createProfile(AdministratorProfile profile) {
        return administratorProfileRepository.save(profile);
    }

    public Optional<AdministratorProfile> findProfileById(Long id) {
        return administratorProfileRepository.findById(id);
    }

    public AdministratorProfile updateProfile(AdministratorProfile profile) {
        return administratorProfileRepository.save(profile);
    }

    public void deleteProfile(Long id) {
        administratorProfileRepository.deleteById(id);
    }

    public List<AdministratorProfile> findAllProfiles() {
        return administratorProfileRepository.findAll();
    }
}
