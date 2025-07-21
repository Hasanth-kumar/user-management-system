package com.ums.auth_service.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    
    // NEW fields
    private String fullName;
    private String phone;
    private String address;
}
