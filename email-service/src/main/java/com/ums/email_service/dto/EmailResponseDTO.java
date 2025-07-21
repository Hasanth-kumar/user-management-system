package com.ums.email_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class EmailResponseDTO {
    private String message;
    private LocalDateTime timestamp;
}
