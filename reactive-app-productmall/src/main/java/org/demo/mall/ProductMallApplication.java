package org.demo.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import reactivefeign.spring.config.EnableReactiveFeignClients;


@EnableEurekaClient
@SpringBootApplication
@EnableReactiveFeignClients(basePackages = {"org.demo.user.api"})
public class ProductMallApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductMallApplication.class);
    }
}
