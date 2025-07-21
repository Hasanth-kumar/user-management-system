package com.ums.user_service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileRequestDTO {

    private String userId;   // ID from auth-service
    private String fullName;
    private String phone;
    private String address;
}
