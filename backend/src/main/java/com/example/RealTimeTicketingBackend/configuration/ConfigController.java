package com.example.RealTimeTicketingBackend.configuration;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/config")
public class ConfigController {
    private final ConfigurationService configurationService;

    public ConfigController(ConfigurationService configurationService) {
        this.configurationService = configurationService;
        this.configurationService.loadConfig(); // Load config at startup
    }

    @PostMapping("/set")
    public String setConfig(@RequestBody Configuration newConfig) {
        configurationService.setConfig(newConfig);
        return "Configuration saved successfully\n";
    }

    @GetMapping("/get")
    public Configuration getConfig() {
        return configurationService.getConfig();
    }
}

