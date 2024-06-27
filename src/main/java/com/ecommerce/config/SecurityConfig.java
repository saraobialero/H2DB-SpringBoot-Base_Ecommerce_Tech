package com.ecommerce.config;

import com.ecommerce.utilities.JwtAuthFilter;
import com.ecommerce.utilities.JwtUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtUtility jwtUtility;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeRequests()
                .requestMatchers(
                        "/ecommerce/api/v1/auth/login",
                        "/ecommerce/api/v1/**",
                        "/h2-console/**",
                        "/v2/api-docs/**",
                        "/v3/api-docs/**",
                        "/swagger-resources/**",
                        "/configuration/ui",
                        "/configuration/security",
                        "/swagger-ui/**",
                        "/webjars/**"
                )
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .headers(headers -> headers
                        .frameOptions(frameOptions -> frameOptions
                                .disable()
                        )
                );;

        //ADD JWT AUTHENTICATION FILTER
        http.addFilterBefore(new JwtAuthFilter(jwtUtility), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}

