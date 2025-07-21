package com.ums.auth_service.client;

import com.ums.auth_service.dto.EmailRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmailClient {

    private final WebClient emailWebClient;

    public void sendEmail(EmailRequestDTO emailRequest) {
        emailWebClient.post()
                .uri("/send")
                .bodyValue(emailRequest)
                .retrieve()
                .bodyToMono(String.class)
                .doOnSuccess(response -> log.info("Email sent successfully"))
                .doOnError(WebClientResponseException.class, e -> {
                    log.error("Email service returned error: {}", e.getResponseBodyAsString());
                })
                .doOnError(Exception.class, e -> {
                    log.error("Email service is unreachable: {}", e.getMessage());
                })
                .onErrorResume(e -> Mono.empty())
                .subscribe(); // Fire and forget
    }
}
// This class is used to send email requests to the email service using WebClient.
// It handles the response and errors, logging them appropriately.