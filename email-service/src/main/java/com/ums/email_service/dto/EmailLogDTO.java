package com.ums.email_service.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class EmailLogDTO {
    private String id;
    private String toEmail;
    private String subject;
    private String body;
    private LocalDateTime sentAt;
}
