/**
 * 
 */
package com.logustecnologia.appPostoCombustivel.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Petronilo Padilha
 * email: nilopadilha@gmail.com
 * Telefone: 84 99498-4982
 *
 */

@Configuration
@EnableSwagger2
public class SwaggerConfiguration extends WebMvcConfigurationSupport{

	@Bean
    public Docket Api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.logustecnologia.appPostoCombustivel"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaInfo());
    }

	 private ApiInfo metaInfo() {

	        ApiInfo apiInfo = new ApiInfo(
	                "Produtos API REST",
	                "API REST de cadastro de processo de abastecimento de posto de combustiveis.",
	                "1.0",
	                "Terms of Service",
	                new Contact("Petronilo Padilha", "https://www.linkedin.com/in/petronilopadilha",
	                        "nilopadilha@gmail.com"),
	                "Apache License Version 2.0",
	                "https://www.apache.org/licesen.html", new ArrayList<VendorExtension>()
	        );

	        return apiInfo;
	    }
}
