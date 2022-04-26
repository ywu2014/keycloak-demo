package com.ywu.keycloak.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        ReactiveJwtAuthenticationConverter converter = new ReactiveJwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(new ReactiveKeycloakRealmRoleConverter());

        http.authorizeExchange()
            .pathMatchers("/actuator/**")
            .permitAll()
            .and()
            .authorizeExchange()
            .anyExchange()
            .authenticated()
            .and()
            .oauth2ResourceServer().jwt().jwtAuthenticationConverter(converter); // to redirect to oauth2 login page.

        return http.build();
    }
}
