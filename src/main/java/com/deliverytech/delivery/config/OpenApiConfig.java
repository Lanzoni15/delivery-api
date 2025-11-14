package com.deliverytech.delivery.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
            .info(new Info()
                .title("DeliveryTech API")
                .version("1.0.0")
                .description("API de Delivery - Documentação OpenAPI")
                .contact(new Contact().name("Arthur Lanzoni").email("seuemail@exemplo.com")));
    }
}
