package com.ums.email_service.controller;

import com.ums.email_service.dto.EmailRequestDTO;
import com.ums.email_service.dto.EmailResponseDTO;
import com.ums.email_service.service.EmailSenderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailSenderService emailSenderService;

    @PostMapping("/send")
    public ResponseEntity<EmailResponseDTO> sendEmail(@RequestBody @Valid EmailRequestDTO request) {
        emailSenderService.sendEmail(request.getTo(), request.getSubject(), request.getBody());
        EmailResponseDTO response = new EmailResponseDTO("Email sent successfully!", LocalDateTime.now());
        return ResponseEntity.ok(response);
    }
}

