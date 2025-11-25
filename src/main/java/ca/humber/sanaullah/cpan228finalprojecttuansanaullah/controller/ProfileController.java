package ca.humber.sanaullah.cpan228finalprojecttuansanaullah.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final Environment environment;

    @Value("${restaurant.name}")
    private String restaurantName;

    @Value("${server.port}")
    private String serverPort;

    @Value("${microservice.secondary.url}")
    private String secondaryServiceUrl;

    public ProfileController(Environment environment) {
        this.environment = environment;
    }

    @GetMapping("/info")
    public ResponseEntity<Map<String, Object>> getProfileInfo() {
        Map<String, Object> info = new HashMap<>();

        String[] activeProfiles = environment.getActiveProfiles();
        String activeProfile = activeProfiles.length > 0 ? activeProfiles[0] : "default";

        info.put("activeProfile", activeProfile);
        info.put("restaurantName", restaurantName);
        info.put("serverPort", serverPort);
        info.put("secondaryServiceUrl", secondaryServiceUrl);
        info.put("h2ConsoleEnabled", environment.getProperty("spring.h2.console.enabled"));
        info.put("showSql", environment.getProperty("spring.jpa.show-sql"));

        return ResponseEntity.ok(info);
    }
}