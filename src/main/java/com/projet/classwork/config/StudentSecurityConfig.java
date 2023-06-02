package com.projet.classwork.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.projet.classwork.service.StudentUserDetailsService;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@Order(1)
@RequiredArgsConstructor
public class StudentSecurityConfig {

	private static final String STUDENT_AUTHORITY_STRING = "STUDENT";

    private final StudentUserDetailsService studentUserDetailsService; 

    @Bean
    public SecurityFilterChain studentSecurityFilterChain(HttpSecurity http) throws Exception {

        return http
                .securityMatchers(matchers -> matchers
                        .requestMatchers("/api/v1/auth/students/**", "/api/v1/students/**"))
                .csrf(csrf->csrf.disable())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(request -> request
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/students/signup").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/students/token").permitAll()
                        .anyRequest().hasAuthority(STUDENT_AUTHORITY_STRING))
                .httpBasic(Customizer.withDefaults())
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .userDetailsService(studentUserDetailsService)
                .build();
    }


}
