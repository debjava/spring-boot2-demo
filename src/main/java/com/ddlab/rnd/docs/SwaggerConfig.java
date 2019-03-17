package com.ddlab.rnd.docs;

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
 * The Class SwaggerConfig is only for Swagger documentation
 *
 * @author Debadatta Mishra
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

  /**
   * Provides basic docket api for the application
   *
   * @return the docket
   */
  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.ddlab.rnd"))
        .paths(PathSelectors.any())
        .build()
        .apiInfo(apiInfo());
  }

  /**
   * Provides the basic api meta data information.
   *
   * @return the api info
   */
  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("REST API for Spring Boot 2.1.3 Version")
        .description("Demo Application for Spring Boot")
        .termsOfServiceUrl("http://springfox.io")
        .contact(new Contact("DDLab INC", "http://www.github.com/debjava", "deba.java@gmail.com"))
        .license("Spring Boot 2.1.3 Version")
        .licenseUrl("http://www.github.com/debjava")
        .version("0.0.1")
        .build();
  }
}
