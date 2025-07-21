package com.ums.user_service.controller;

import com.ums.user_service.dto.UserProfileRequestDTO;
import com.ums.user_service.dto.UserProfileResponseDTO;
import com.ums.user_service.model.UserProfile;
import com.ums.user_service.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/profiles")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @PostMapping
    public UserProfileResponseDTO createProfile(@RequestBody UserProfileRequestDTO profileRequest) {
        UserProfile profile = convertToEntity(profileRequest);
        return convertToResponseDTO(userProfileService.createProfile(profile));
    }

    @GetMapping("/{id}")
    public UserProfileResponseDTO getProfileById(@PathVariable String id) {
        return userProfileService.getProfileById(id)
                .map(this::convertToResponseDTO)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
    }

    @GetMapping("/user/{userId}")
    public UserProfileResponseDTO getProfileByUserId(@PathVariable String userId) {
        return userProfileService.getProfileByUserId(userId)
                .map(this::convertToResponseDTO)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
    }

    @GetMapping
    public List<UserProfileResponseDTO> getAllProfiles() {
        return userProfileService.getAllProfiles()
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public UserProfileResponseDTO updateProfile(@PathVariable String id, @RequestBody UserProfileRequestDTO profileRequest) {
        UserProfile updatedProfile = convertToEntity(profileRequest);
        return convertToResponseDTO(userProfileService.updateProfile(id, updatedProfile));
    }

    @DeleteMapping("/{id}")
    public void deleteProfile(@PathVariable String id) {
        userProfileService.deleteProfile(id);
    }

    // DTO Mapping Methods

    private UserProfile convertToEntity(UserProfileRequestDTO dto) {
        return UserProfile.builder()
                .userId(dto.getUserId())
                .fullName(dto.getFullName())
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .build();
    }

    private UserProfileResponseDTO convertToResponseDTO(UserProfile profile) {
        return UserProfileResponseDTO.builder()
                .id(profile.getId())
                .userId(profile.getUserId())
                .fullName(profile.getFullName())
                .phone(profile.getPhone())
                .address(profile.getAddress())
                .build();
    }
}
// This controller handles HTTP requests related to user profiles.
// It provides endpoints for creating, retrieving, updating, and deleting user profiles.