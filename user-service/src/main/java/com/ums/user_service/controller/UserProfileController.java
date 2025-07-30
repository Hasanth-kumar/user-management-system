package com.ums.user_service.controller;

import com.ums.user_service.dto.UserProfileRequestDTO;
import com.ums.user_service.dto.UserProfileResponseDTO;
import com.ums.user_service.model.UserProfile;
import com.ums.user_service.service.UserProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/profiles")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @PostMapping
    public ResponseEntity<UserProfileResponseDTO> createProfile(@RequestBody @Valid UserProfileRequestDTO profileRequest) {
        UserProfile profile = convertToEntity(profileRequest);
        UserProfile savedProfile = userProfileService.createProfile(profile);
        return ResponseEntity.ok(convertToResponseDTO(savedProfile));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserProfileResponseDTO> getProfileById(@PathVariable String id) {
        return userProfileService.getProfileById(id)
                .map(profile -> ResponseEntity.ok(convertToResponseDTO(profile)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserProfileResponseDTO> getProfileByUserId(@PathVariable String userId) {
        return userProfileService.getProfileByUserId(userId)
                .map(profile -> ResponseEntity.ok(convertToResponseDTO(profile)))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<UserProfileResponseDTO>> getAllProfiles() {
        List<UserProfileResponseDTO> profiles = userProfileService.getAllProfiles()
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(profiles);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserProfileResponseDTO> updateProfile(@PathVariable String id, @RequestBody @Valid UserProfileRequestDTO profileRequest) {
        try {
            UserProfile updatedProfile = convertToEntity(profileRequest);
            UserProfile savedProfile = userProfileService.updateProfile(id, updatedProfile);
            return ResponseEntity.ok(convertToResponseDTO(savedProfile));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable String id) {
        try {
            userProfileService.deleteProfile(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DTO Mapping Methods

    private UserProfile convertToEntity(UserProfileRequestDTO dto) {
        return UserProfile.builder()
                .userId(dto.getUserId())
                .fullName(dto.getFullName()) // This will use the computed getFullName() method
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