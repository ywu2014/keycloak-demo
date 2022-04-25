package com.ywu.keycloak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
public class KeycloakResourceServerApplication {
    public static void main( String[] args ) {
        SpringApplication.run(KeycloakResourceServerApplication.class, args);
    }
}
