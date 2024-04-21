package com.company.books.backend.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.company.books.backend.filter.JwtReqFilter;

@Configuration
public class ConfigSecurity {
	
	@Autowired
	@Lazy
	private JwtReqFilter jwtReqFilter;

	// metodo usado para configurar la app para que use las tablas predefinidas de users y authorities
	@Bean
	public UserDetailsManager userDetailsManager(DataSource dataSource) {
		return new JdbcUserDetailsManager(dataSource);
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(configure ->{
			configure
				.requestMatchers(HttpMethod.GET, "/v1/books").hasRole("Empleado")
				.requestMatchers(HttpMethod.GET, "/v1/books/**").hasRole("Empleado")
				.requestMatchers(HttpMethod.POST, "/v1/books").hasRole("Jefe")
				.requestMatchers(HttpMethod.PUT, "/v1/books/**").hasRole("Jefe")
				.requestMatchers(HttpMethod.DELETE, "/v1/books/**").hasRole("Jefe")
				.requestMatchers(HttpMethod.GET, "/v1/categories").hasRole("Empleado")
				.requestMatchers(HttpMethod.GET, "/v1/categories/**").hasRole("Empleado")
				.requestMatchers(HttpMethod.POST, "/v1/categories").hasRole("Jefe")
				.requestMatchers(HttpMethod.PUT, "/v1/categories/**").hasRole("Jefe")
				.requestMatchers(HttpMethod.DELETE, "/v1/categories/**").hasRole("Jefe")
				.requestMatchers("/v1/authenticate", "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll();
		})
		
		.addFilterBefore(jwtReqFilter, UsernamePasswordAuthenticationFilter.class)
		.sessionManagement( (session) -> session
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		);
		
		http.httpBasic(Customizer.withDefaults());
		
		// deshabilitar CSRF (CROSS SITE REQUEST FORGERY)
		http.csrf( csrf -> csrf.disable());
		
		return http.build();
	}
	
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();	
	}
	
	/* Prev Spring Secutity & JDBC
	 * 
	 * import org.springframework.security.core.userdetails.User;
	 * import org.springframework.security.core.userdetails.UserDetails;
	 * import org.springframework.security.provisioning.InMemoryUserDetailsManager;
	 * 
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
	*/
	
}
