package com.ecom.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
public class SecurityConfig {
private JWTFiler jwtFiler;

    public SecurityConfig(JWTFiler jwtFiler) {
        this.jwtFiler = jwtFiler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable();
        http.addFilterBefore(jwtFiler, AuthorizationFilter.class);
        http.authorizeHttpRequests().anyRequest().permitAll();

        return http.build();
    }
}
