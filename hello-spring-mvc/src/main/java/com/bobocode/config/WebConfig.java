package com.bobocode.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * This class provides web (servlet) related configuration.
 * <p>
 * todo: mark this class as Spring config class
 * todo: enable web mvc using annotation
 * todo: enable component scanning for package "web"
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.bobocode.web")
public class WebConfig {
}
