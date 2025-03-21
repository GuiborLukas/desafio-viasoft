package com.gmail.lgsc92.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    
    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("API de Email")
                .description("Documentação da API para envio de e-mails via AWS/OCI")
                .version("v1.0"))
            .externalDocs(new ExternalDocumentation()
                .description("Repositório do Projeto")
                .url("https://github.com/seu-usuario/seu-repositorio"));
    }
}
