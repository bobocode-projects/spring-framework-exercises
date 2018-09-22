package com.bobocode.config;

import com.bobocode.TestDataGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * This class application context configuration.
 * <p>
 * todo: make this class a Spring configuration class
 * todo: enable component scanning for dao and service packages
 * todo: configure a bean of type {@link TestDataGenerator} with name "dataGenerator". Don't specify bean name explicitly
 */
@Configuration
@ComponentScan(basePackages = {"com.bobocode.dao","com.bobocode.service"})
public class AppConfig {

    @Bean
    public TestDataGenerator dataGenerator() {
        return new TestDataGenerator();
    }
}
