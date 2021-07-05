package com.ghost.perfilProfissional.config;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ghost.perfilProfissional"))
                .paths(regex(".*"))
                .build()
                .apiInfo(metaInfo());
    }

    private ApiInfo metaInfo() {

        ApiInfo apiInfo = new ApiInfo(
                "API PERFIL PROFISSIONAL",
                "Api de perfil profissional e seu contatos. <br>" +
                        "DOCUMENTAÇÃO COMPLETA E DETALHADA: https://documenter.getpostman.com/view/5414747/Tzm2KdxK <br>" +
                        "REPOSITORIO COM MAIS INFORMAÇÕES: https://github.com/sauloiot/perfil-profissional/blob/main/README.md: ",
                "1.0",
                "Terms of Service",
                new Contact("Saulo Tavares", "https://github.com/sauloiot",
                        "saulo.11@hotmail.com"),
                "CC BY-NC",
                "https://creativecommons.org/licenses/by-nc/4.0/legalcode", new ArrayList<VendorExtension>()
        );

        return apiInfo;
    }

}