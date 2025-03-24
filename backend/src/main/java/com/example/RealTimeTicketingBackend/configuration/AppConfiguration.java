package com.example.RealTimeTicketingBackend.configuration;

import org.springframework.context.annotation.Bean;


@org.springframework.context.annotation.Configuration
public class AppConfiguration {
    @Bean
    public Configuration configuration() {
        return new Configuration();
    }
}

