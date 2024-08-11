package com.mycompany.carwash.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Carwash API",
        version = "v1",
        description = "Documentação da API do sistema de gerenciamento de lava jato"
    )
)
public class OpenApiConfig {
    // Você pode adicionar outras configurações aqui, se necessário.
}
