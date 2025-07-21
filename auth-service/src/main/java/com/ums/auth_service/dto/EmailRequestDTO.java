package com.ums.auth_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailRequestDTO {
    private String toEmail;
    private String subject;
    private String body;
}
// This class is used to send email requests from the auth service to the email service.
// It contains the recipient's email, subject, and body of the email.