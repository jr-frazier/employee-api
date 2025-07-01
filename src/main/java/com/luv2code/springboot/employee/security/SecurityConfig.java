package com.luv2code.springboot.employee.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);

        // define query retrieve a user by username
        manager.setUsersByUsernameQuery("select user_id, password, active from system_users where user_id=?");

        // define query to retrieve the authorities/roles by username
        manager.setAuthoritiesByUsernameQuery("select user_id, role from roles where user_id=?");

        return manager;

    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, "/h2-console/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/h2-console/**").permitAll()
                        .requestMatchers("/docs/**", "swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/employees/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
                );

        // Disable Basic rules
        http.httpBasic(httpBasicCustomizer -> httpBasicCustomizer.disable());

        // Use HTTP Basic Auth
        http.httpBasic(Customizer.withDefaults());

        // Disable CSRF
        http.csrf(csrf -> csrf.disable());

        http.exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(authenticationEntryPoint()));

        http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));
        return http.build();
    }

    /**
     * Creates an instance of {@link AuthenticationEntryPoint} to handle unauthorized access attempts.
     * This entry point sets the HTTP response status to 401 (Unauthorized),
     * configures the response content type as "application/json",
     * and provides a JSON-formatted error message indicating unauthorized access.
     *
     * @return a configured {@link AuthenticationEntryPoint} that defines how to handle unauthorized requests.
     */
    @Bean
    public AuthenticationEntryPoint authenticationEntryPoint() {
        return (request, response, authException) -> {
            // Send 401 unauthorzed status without triggering a basic auth
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json");

            // Removes the WWW-Authenticate header to prevent browser popup
            response.setHeader("WWW-Authenticate",  "");

            response.getWriter().write("{\"error\": \"Unauthorized acceass\"}");

        };
    }
}
