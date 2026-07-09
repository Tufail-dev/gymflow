package com.gymflow.gymflow.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {

        // CORS configuration object
        CorsConfiguration config = new CorsConfiguration();

        // React frontend ko request bhejne ki permission
        config.addAllowedOrigin("http://localhost:5173");

        // Sab headers allow
        config.addAllowedHeader("*");

        // GET, POST, PUT, DELETE... sab methods allow
        config.addAllowedMethod("*");

        // Cookies, Session, JWT credentials allow
        config.setAllowCredentials(true);

        // CORS configuration store karne ke liye source object
        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        // Ye configuration project ki sabhi APIs par apply hogi
        source.registerCorsConfiguration("/**", config);

        // Spring Boot ko CORS filter return kar do
        return new CorsFilter(source);
    }
}