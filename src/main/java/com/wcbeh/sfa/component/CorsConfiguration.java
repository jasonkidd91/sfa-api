package com.wcbeh.sfa.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    @Value("${spring.profiles.active}") // Get the active profile
    private String activeProfile;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        if ("dev".equals(activeProfile)) {
            registry.addMapping("/**") // Allow CORS for all endpoints
                    .allowedOrigins("http://localhost:3000") // Allow requests from localhost:3000
                    .allowedMethods("GET", "POST", "PUT", "DELETE") // Allow specified HTTP methods
                    .allowedHeaders("*") // Allow all headers
                    .allowCredentials(true); // Allow credentials (e.g., cookies)
        }
    }
}

