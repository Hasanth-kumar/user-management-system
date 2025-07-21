package com.ums.auth_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient emailWebClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8083/api/email") // Change if different
                .build();
    }

    @Bean
    public WebClient userWebClient() {
        return WebClient.builder()
                .baseUrl("http://localhost:8082/api/profiles") // user-service base URL
                .build();
    }

}