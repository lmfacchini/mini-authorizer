package com.vr.miniauthorizer.ws.app;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages="com.vr.miniauthorizer.core.repository")
@EnableTransactionManagement
@EntityScan("com.vr.miniauthorizer.domain")
@ComponentScan({MiniAuthorizerConfiguration.CONTROLLER_BASE_PACKAGE, "com.vr.miniauthorizer.core"})
@EnableSwagger2
public class MiniAuthorizerConfiguration {

    public final static String CONTROLLER_BASE_PACKAGE = "com.vr.miniauthorizer.ws";


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(CONTROLLER_BASE_PACKAGE))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    protected ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger - Mini Authorizer")
                .description("Card Authorizer")
                .version("1.0")
                .build();
    }

}
