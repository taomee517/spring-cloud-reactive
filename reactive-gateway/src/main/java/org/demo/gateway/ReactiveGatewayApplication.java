package org.demo.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ReactiveGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReactiveGatewayApplication.class);
    }
}
