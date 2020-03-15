package org.woodwhales.starter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @projectName: woodwhales-spring-boot-starter
 * @author: woodwhales
 * @date: 20.3.14 17:37
 * @description:
 */
@Configuration
@ConditionalOnWebApplication // 只有是web应用才生效
@EnableConfigurationProperties(HelloProperties.class)
public class HelloServiceAutoConfiguration {
    @Autowired
    private HelloProperties helloProperties;

    @Bean
    public HelloService helloService() {
        return new HelloService(helloProperties);
    }
}
