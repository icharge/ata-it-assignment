package com.atait.assignment.funds.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("ATA-IT Assignment - Fund API")
                        .version("1.0.0")
                        .description("REST API for placing fund orders (Mutual Funds, Index Funds, Fixed Income) with validation rules")
                        .contact(new Contact()
                                .name("Charge (Norrapat Nimmanee)")
                                .email("inorrapat@gmail.com")
                                .url("https://github.com/icharge/ata-it-assignment"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT"))
                );
    }
}
