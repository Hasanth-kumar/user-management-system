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
                    // Only update fields that are not null
                    if (updatedProfile.getFullName() != null) {
                        existing.setFullName(updatedProfile.getFullName());
                    }
                    if (updatedProfile.getPhone() != null) {
                        existing.setPhone(updatedProfile.getPhone());
                    }
                    if (updatedProfile.getAddress() != null) {
                        existing.setAddress(updatedProfile.getAddress());
                    }
                    if (updatedProfile.getUserId() != null) {
                        existing.setUserId(updatedProfile.getUserId());
                    }
                    return userProfileRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Profile not found with id: " + id));
    }

    public void deleteProfile(String id) {
        if (!userProfileRepository.existsById(id)) {
            throw new RuntimeException("Profile not found with id: " + id);
        }
        userProfileRepository.deleteById(id);
    }
}
