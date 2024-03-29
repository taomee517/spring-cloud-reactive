package org.demo.mall.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger-ui路径调整为：http://localhost:port/swagger-ui/index.html#/
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .description("product mall")
                        .title("Reactive API")
                        .version("1.0.0")
                        .build())
                .select()
//                .apis(RequestHandlerSelectors.any())
                // 必须加Api注解才能生效
//                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                // 指定controller处理类的package路径
                .apis(RequestHandlerSelectors.basePackage("org.demo.mall.controller"))
                .paths(PathSelectors.any())
                .build();

    }
}

