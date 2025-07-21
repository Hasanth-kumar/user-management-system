package com.ums.auth_service.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAuth {
    @Id
    private String id;
    private String username;
    private String email;
    private String password;
}
