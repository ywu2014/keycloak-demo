package com.ywu.keycloak.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(new KeycloakRealmRoleConverter());

        http.authorizeRequests(authz -> authz
                .antMatchers(HttpMethod.GET, "/testing/").permitAll()
                .antMatchers(HttpMethod.GET, "/protected/**").hasRole("ADMIN")
                .anyRequest().authenticated())
                .oauth2ResourceServer().jwt().jwtAuthenticationConverter(converter);
    }
}
