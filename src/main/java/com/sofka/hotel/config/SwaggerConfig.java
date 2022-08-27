package com.sofka.hotel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


/**
 * Configuración Swagger para la generación de documentación de la API REST
 * HTML: http://localhost:8080/swagger-ui/
 **/
@Configuration
public class SwaggerConfig {

    /**
     * Indica que el retorno es un bean disponible para el contenedor beans,
     * Spring lo inyectara y generará una web con la documentación
     **/
    @Bean
    public Docket api () {


        /**
         * Instanciación y retorno del Docket
         * Devuelve objeto Docket, es decir un builder
         **/
        return new Docket (DocumentationType.SWAGGER_2)
                .apiInfo(apiDetails())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sofka.hotel.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiDetails () {
        return new ApiInfoBuilder().title("Spring Boot API REST")
                .description("Rest Api Client From Hotel")
                .license("Apache 2.0")
                .version("1.0.0")
                //.licenseUrl("https://portafolio-web-one.vercel.app/")
                .contact(new Contact("Andres Felipe Taborda Arias", "https://github.com/AndresTabor", "andrestaborda43@gmail.com"))
                .build();
    }
}