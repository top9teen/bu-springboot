package com.it.app.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Value("${spring.application.name}")
    private String applicationName;

    @Value("${build.version}")
    private String buildVersion;
    
    
	@Bean
	public Docket api() {                
	    return new Docket(DocumentationType.SWAGGER_2)          
	      .select()                                       
	      .apis(RequestHandlerSelectors.basePackage("com.it.app.controller"))
	      .paths(PathSelectors.any())                     
	      .build();
/*	      .apiInfo(new ApiInfoBuilder()
					.version(buildVersion)
					.title(applicationName)
					.description("").build())*/
	}
	
}
