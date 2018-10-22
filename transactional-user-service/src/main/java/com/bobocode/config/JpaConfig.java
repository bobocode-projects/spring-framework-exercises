package com.bobocode.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

/**
 * {@link JpaConfig} provides configuration required to create {@link javax.persistence.EntityManagerFactory}. In order
 * to configure {@link javax.persistence.EntityManagerFactory} Spring needs to configure two beans {@link DataSource}
 * and {@link JpaVendorAdapter}.
 * <p>
 * todo: 1. Mark this class as spring config
 * todo: 2. Configure a bean of {@link DataSource}
 * todo: 3. Configure a bean of {@link JpaVendorAdapter}
 * todo: 4. Set adapter database to tell Hibernate which dialect to use
 * todo: 5. Configure bean {@link javax.persistence.EntityManagerFactory} with name "entityManagerFactory"
 * todo: 6. Configure package "com.bobocode.model" to scan for JPA entities
 *
 */
@Configuration
public class JpaConfig {
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .build();
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        // todo: 4. Set adapter database to tell Hibernate which dialect to use
        adapter.setDatabase(Database.H2);
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true); // this sets hibernate.hbm2ddl.auto=update (Hibernate will generate db tables)
        return adapter;
    }

    @Bean("entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean localContainerEMF(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setJpaVendorAdapter(jpaVendorAdapter);
        emf.setPackagesToScan("com.bobocode.model");
        // todo: 6. Configure package "com.bobocode.model" to scan for JPA entities
        return emf;
    }
}

