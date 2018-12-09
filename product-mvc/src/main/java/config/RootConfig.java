package config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import util.ProductGenerator;

@Configuration
@ComponentScan(basePackages = {"service"})
public class RootConfig {

}
