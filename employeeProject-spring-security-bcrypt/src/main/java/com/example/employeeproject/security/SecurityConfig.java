package com.example.employeeproject.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // add support for jdbc authentication
    // this time, i changed the user table to use bcrypt password encoder
    // it is using spring security's default user table
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    // Restrinc access to the endpoints
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests -> requests
                .requestMatchers(HttpMethod.GET, "/employee/**").hasRole("EMPLOYEE")
                .requestMatchers(HttpMethod.GET, "/employee").hasRole("EMPLOYEE")

                .requestMatchers(HttpMethod.POST, "/employee").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT, "/employee/**").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PATCH, "/employee/**").hasRole("MANAGER")

                .requestMatchers(HttpMethod.DELETE, "/employee/**").hasRole("ADMIN")
                .anyRequest().authenticated())
            .httpBasic(Customizer.withDefaults()) 
            .csrf(AbstractHttpConfigurer::disable); 

        return http.build();
    }
}

