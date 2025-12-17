package com.recruiterproof.codegenerator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

/**
 * OpenAPI/Swagger configuration for the application.
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("CodeGenerator REST API")
                        .version("1.0.0")
                        .description("A REST API that automatically generates C# code for Visual Studio")
                        .contact(new Contact()
                                .name("Gaetano Cerciello")
                                .url("https://github.com/GaetanoCerciello/CodeGenerator")));
    }
}
