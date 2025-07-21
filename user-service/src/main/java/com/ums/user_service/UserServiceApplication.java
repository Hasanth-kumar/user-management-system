package com.ums.user_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}
// This is the main entry point for the User Service application.
// It uses Spring Boot's @SpringBootApplication annotation to enable auto-configuration, component scanning, and configuration properties.
// The main method starts the application by calling SpringApplication.run() with the UserServiceApplication class and command-line arguments.
// This application will handle user profile management, including viewing and updating user profiles.
// It will also provide endpoints for user profile operations, such as creating, updating, and deleting user profiles.
// The User Service will be responsible for managing user data, including user profiles, preferences, and settings.
// It will also handle user-related operations, such as retrieving user profiles and updating user information.
// The application will be configured to use JWT tokens for authentication, which will be validated by the Auth Service.		
