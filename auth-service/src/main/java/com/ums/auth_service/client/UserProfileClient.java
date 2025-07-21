package com.ums.auth_service.client;

import com.ums.auth_service.dto.UserProfileRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserProfileClient {

    private final WebClient userWebClient;

    public void createUserProfile(UserProfileRequestDTO request) {
        userWebClient.post()
                
                .bodyValue(request)
                .retrieve()
                .bodyToMono(Void.class)
                .doOnSuccess(response -> log.info("User profile created successfully."))
                .doOnError(WebClientResponseException.class, e -> 
                    log.error("User service returned error: {}", e.getResponseBodyAsString()))
                .doOnError(Exception.class, e ->
                    log.error("User service is unreachable: {}", e.getMessage()))
                .onErrorResume(e -> Mono.empty()) // optional: prevent registration failure if user-service is down
                .subscribe(); // fire-and-forget
    }
}
// This class is used to create user profiles in the user service using WebClient.
// It handles the response and errors, logging them appropriately.