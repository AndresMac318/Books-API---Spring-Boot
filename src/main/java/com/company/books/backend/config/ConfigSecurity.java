package com.company.books.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ConfigSecurity {

	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {
		
		UserDetails andres = User.builder()
				.username("andres")
				.password("{noop}andres123")
				.roles("Empleado")
				.build();
		
		UserDetails carlos = User.builder()
				.username("carlos")
				.password("{noop}carlos123")
				.roles("Empleado", "Jefe")
				.build();
		
		UserDetails edita = User.builder()
				.username("edita")
				.password("{noop}edita123")
				.roles("Empleado", "Jefe")
				.build();
		
		return new InMemoryUserDetailsManager(andres, carlos, edita);
	} 
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(configure ->{
			configure
				.requestMatchers(HttpMethod.GET, "/v1/books").hasRole("Empleado")
				.requestMatchers(HttpMethod.GET, "/v1/books/**").hasRole("Empleado")
				.requestMatchers(HttpMethod.POST, "/v1/books").hasRole("Jefe")
				.requestMatchers(HttpMethod.PUT, "/v1/books/**").hasRole("Jefe")
				.requestMatchers(HttpMethod.DELETE, "/v1/books/**").hasRole("Jefe");
		});
		
		http.httpBasic(Customizer.withDefaults());
		
		// deshabilitar CSRF (CROSS SITE REQUEST FORGERY)
		http.csrf( csrf -> csrf.disable());
		
		return http.build();
	}
	
}
