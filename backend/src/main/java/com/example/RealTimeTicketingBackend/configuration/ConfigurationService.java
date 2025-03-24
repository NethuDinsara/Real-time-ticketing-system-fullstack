package com.example.RealTimeTicketingBackend.configuration;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationService {
    private Configuration config = new Configuration();

    @PostConstruct
    public void init() {
        loadConfig(); // Load configuration at startup
    }

    public void loadConfig() {
        config.loadConfig(); // Load from file
    }

    public Configuration getConfig() {
        return config;
    }

    public void setConfig(Configuration newConfig) {
        this.config = newConfig;
        config.saveConfig(); // Save whenever it's set
    }
}
