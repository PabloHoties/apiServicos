package br.com.cotiinformatica.infrastructure.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.cotiinformatica.infrastructure.filters.AuthenticationFilter;

@Configuration
public class AuthenticationConfig {

	@Bean
	public FilterRegistrationBean<AuthenticationFilter> jwtFilter() {

		// registrando o filter que irá validar os tokens
		FilterRegistrationBean<AuthenticationFilter> filter = new FilterRegistrationBean<AuthenticationFilter>();

		filter.setFilter(new AuthenticationFilter());

		// mapeando toda a API para exigir o token de autenticação
		filter.addUrlPatterns("/api/profissionais/*");
		filter.addUrlPatterns("/api/servicos/*");
		return filter;
	}
}
