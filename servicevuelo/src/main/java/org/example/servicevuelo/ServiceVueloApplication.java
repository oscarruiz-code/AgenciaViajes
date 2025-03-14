package org.example.servicevuelo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ServiceVueloApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceVueloApplication.class, args);
    }
}