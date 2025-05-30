package com.gmail.lgsc92.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Envio de E-mails")
                        .description("Esta API permite o envio de e-mails utilizando as plataformas AWS ou OCI.")
                        .version("1.0.0"));
    }
}
