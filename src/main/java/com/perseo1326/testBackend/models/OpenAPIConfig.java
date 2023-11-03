package com.perseo1326.testBackend.models;

import java.util.List;

import io.swagger.v3.oas.models.ExternalDocumentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {

    @Value("${perseo1326.openapi.dev-url}")
    private String devUrl;

//    @Value("${perseo1326.openapi.prod-url}")
//    private String prodUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Servidor en entorno de Desarrollo");

//        Server prodServer = new Server();
//        prodServer.setUrl(prodUrl);
//        prodServer.setDescription("Server URL in Production environment");

        Contact contact = new Contact();
        contact.setEmail("jskolik@yahoo.com");
        contact.setName("John Skolik");
//        contact.setUrl("www.linkedin.com/in/jskolik");


        Info info = new Info()
                .title("Jump2Digital Skins Backend Test API")
                .version("1.0")
                .contact(contact)
                .description("API que gestiona una lista de skins para una aplicación externa y sus usuarios.");

        ExternalDocumentation externalDocumentation = new ExternalDocumentation().description("LinkedIn - John Skolik").url("https://www.linkedin.com/in/jskolik");

        return new OpenAPI().info(info).servers(List.of(devServer)).externalDocs(externalDocumentation);
    }
}
