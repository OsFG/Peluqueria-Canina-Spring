package com.mycompany.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
                .info(new Info()
                        .title("API PeluqueriaCanina")
                        .description("API REST de la aplicación PeluqueriaCanina, que contiene las funcionalidades de CRUD para registrar información de Duenios, Mascotas y Citas")
                        .contact(new Contact()
                                .name("Oscar Fano")
                                .url("https://www.linkedin.com/in/oscar-fano-g/"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://voll.med/api/licencia")));
    }

    /*@Bean
    public void message(){
        System.out.println("Bearer is working");
    }
    */
}
