package com.ums.user_service.service;

import com.ums.user_service.model.UserProfile;
import com.ums.user_service.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    public UserProfile createProfile(UserProfile profile) {
        return userProfileRepository.save(profile);
    }

    public Optional<UserProfile> getProfileById(String id) {
        return userProfileRepository.findById(id);
    }

    public Optional<UserProfile> getProfileByUserId(String userId) {
        return userProfileRepository.findByUserId(userId);
    }

    public List<UserProfile> getAllProfiles() {
        return userProfileRepository.findAll();
    }

    public UserProfile updateProfile(String id, UserProfile updatedProfile) {
        return userProfileRepository.findById(id)
                .map(existing -> {
                    existing.setFullName(updatedProfile.getFullName());
                    existing.setPhone(updatedProfile.getPhone());
                    existing.setAddress(updatedProfile.getAddress());
                    return userProfileRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Profile not found"));
    }

    public void deleteProfile(String id) {
        userProfileRepository.deleteById(id);
    }
}
