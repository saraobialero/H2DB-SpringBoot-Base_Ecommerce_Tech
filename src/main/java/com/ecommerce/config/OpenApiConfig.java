package com.ecommerce.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info = @Info(
        contact = @Contact(
                name = "Sara"
        ),
        description = "API Rest that offers services for basic ecommerce",
        title = "Ecommerce api",
        version = "1.0"
    ),
        servers = {
        @Server(
                description = "Local environment",
                url = "http://localhost:8080"
        )
        }

)
@SecurityScheme(name = "baerer",
                description = "JWT auth desc",
                scheme = "bearer",
                type = SecuritySchemeType.HTTP,
                bearerFormat = "JWT",
                in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
