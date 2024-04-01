package br.com.cotiinformatica.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		
		return new OpenAPI().components(new Components())
				.info(new Info()
						.title("API Serviços - COTI Informática")
						.description("Projeto Spring Boot API para controle de serviços")
						.version("v1"));
	}
}
