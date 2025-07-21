package com.ums.auth_service.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileRequestDTO {
    private String userId;
    private String fullName;
    private String phone;
    private String address;
}
