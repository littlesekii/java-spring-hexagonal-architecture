package com.littlesekii.hexagonal_architecture.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI appOpenAPI() {
        return new OpenAPI().info(
            new Info().title("Hexagonal Architecture API")
            .description("A example API made with Hexagonal Architecture.")
            .version("v1.0.0")
        );
    }
}
