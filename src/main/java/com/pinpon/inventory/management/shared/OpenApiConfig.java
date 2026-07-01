package com.pinpon.inventory.management.shared;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT",
        description = "JWT Authorization header using the Bearer scheme."
)

public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                .title("Inventory Management")
                .version("1.0")
                .description("""
                        REST API for managing
                        -Inventory
                        -Movements
                        -Products
                        -Purchases
                        -Suppliers
                        -Users
                        -Warehouses
                        """)
                        .contact(new Contact()
                                .name("pinponalv")
                                .url("https://github.com/pinponalv")
                                .email("correo@personal.com")));
    }
}
