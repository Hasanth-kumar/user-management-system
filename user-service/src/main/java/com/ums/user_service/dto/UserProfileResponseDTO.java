package com.ums.user_service.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileResponseDTO {

    private String id;
    private String userId;
    private String fullName;
    private String phone;
    private String address;
}
// This DTO is used to send user profile data back to the client.
// It includes the user ID, full name, phone number, and address.
