package com.salesforce.tooling.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Salesforce PET swagger configuration.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    
    /**
     * Configures the PET apis.
     * 
     * @return configured swagger API bean.
     */
    @Bean
    public Docket petApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
            .apis(RequestHandlerSelectors.basePackage("com.salesforce.tooling.controller"))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(this.metaData());
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder().title("Salesforce Production Engineering Tools")
            .description("\"REST APIs for Salesforce production engineering tools\"")
            .version("1.0.0")
            .contact(new Contact("Salesforce Production Engineering Tools Department", "http://salesforce.com", "pet@salesforce.com"))
            .build();
    }
}