package com.example.employeeproject.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {

        UserDetails john = User.withUsername("john")
                .password("{noop}password123") // {noop} indicates that the password is stored in plain text
                .roles("EMPLOYEE")
                .build();

        UserDetails marry = User.withUsername("marry")
                .password("{noop}password123") // {noop} indicates that the password is stored in plain text
                .roles("EMPLOYEE", "MANAGER")
                .build();

        UserDetails Katy = User.withUsername("katy")
                .password("{noop}password123") // {noop} indicates that the password is stored in plain text
                .roles("EMPLOYEE", "ADMIN", "MANAGER")
                .build();

        
        return new InMemoryUserDetailsManager(john, marry, Katy);
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
            .httpBasic(Customizer.withDefaults()) // Enable basic authentication
            .csrf(AbstractHttpConfigurer::disable); 

        return http.build();
    }
}
