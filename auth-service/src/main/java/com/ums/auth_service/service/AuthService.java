package com.ums.auth_service.service;

import com.ums.auth_service.client.EmailClient;
import com.ums.auth_service.client.UserProfileClient;
import com.ums.auth_service.dto.*;
import com.ums.auth_service.model.UserAuth;
import com.ums.auth_service.respository.UserAuthRepository;
import com.ums.auth_service.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserAuthRepository repository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final EmailClient emailClient;
    private final UserProfileClient userProfileClient;

    public String register(RegisterRequest request) {
        if (repository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("A user with email '" + request.getEmail() + "' already exists.");
        }


        UserAuth user = UserAuth.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        repository.save(user);

        // Send welcome email
        emailClient.sendEmail(new EmailRequestDTO(
                user.getEmail(),
                "Welcome to UMS!",
                "Hello " + user.getUsername() + ", your account has been created."
        ));

        // Create user profile (with placeholders for now)
        userProfileClient.createUserProfile(UserProfileRequestDTO.builder()
                .userId(user.getId())
                .fullName(request.getUsername()) // You can extend RegisterRequest to capture full name if needed
                .phone(request.getPhone())
                .address(request.getAddress())
                .build());

        return "User registered successfully!";
    }

    public AuthResponse login(LoginRequest request) {
        UserAuth user = repository.findByEmail(request.getEmail())
        .orElseThrow(() -> new RuntimeException("No user found with email: " + request.getEmail()));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Incorrect password for email: " + request.getEmail());
        }

        String token = jwtUtil.generateToken(user.getId(), user.getUsername(), user.getEmail());
        return new AuthResponse(token);
    }
}

// This service handles user registration and login.
// It interacts with the UserAuthRepository to manage user data,
// uses JwtUtil to generate JWT tokens, and communicates with EmailClient and UserProfileClient