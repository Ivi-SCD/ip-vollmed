package br.com.ipvoll.infra.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfigurations {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
                .info(new Info()
                        .title("IPVollMed API")
                        .description("API Rest of IPVollMed application, include CRUD of doctors and patients, in addition to scheduling and canceling appointments")
                        .contact(new Contact()
                                .name("Backend Team")
                                .email("ivipnascimento@hotmail.com"))
                        .license(new License()
                                .name("MIT")
                                .url("http://ipvollmed/api/license")));
    }
}
