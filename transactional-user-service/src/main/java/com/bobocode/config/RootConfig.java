package com.bobocode.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * This class provides root application configuration. It scans for all available beans and enables declarative
 * transaction management.
 * <p>
 * todo: 1. Mark this class as config
 * todo: 2. Enable component scanning for package "com.bobocode"
 * todo: 3. Configure JPA {@link PlatformTransactionManager} with bean name "transactionManager"
 * todo: 4. Enable declarative transaction management
 */
@Configuration
@ComponentScan(basePackages = "com.bobocode")
@EnableTransactionManagement
public class RootConfig {
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }
}
