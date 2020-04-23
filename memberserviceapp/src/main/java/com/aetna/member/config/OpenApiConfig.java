package com.aetna.member.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        Contact defaultContact = new Contact();
        defaultContact.setName("Aetna Member Services");
        defaultContact.setUrl("");

        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Aetna Member Service APIs")
                        .description("Aetna Member Service API Swagger Documentation!")
                        .contact(defaultContact)
                        .version("1.0.0"));
    }

}
