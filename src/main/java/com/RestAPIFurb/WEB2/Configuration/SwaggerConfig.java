package com.RestAPIFurb.WEB2.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiDocConfig() {
        return new OpenAPI()
                .info(new Info()
                        .title("Prova de Suficiência WEB 2 - FURB")
                        .description("Documentação da aplicação com endpoins cadastrados e requisitos necessários.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Henrique B.")
                                .email("pedrohenriqueborba1@gmail.com")
                                .url("https://github.com/SkBolttz"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Documentação Completa")
                        .url("https://swagger.io/docs/"));
    }
}