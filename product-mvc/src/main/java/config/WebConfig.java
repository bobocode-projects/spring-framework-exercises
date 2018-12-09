package config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import service.ProductService;
import service.ProductServiceInterface;
import util.ProductGenerator;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "web")
public class WebConfig {

    @Bean
    public ProductGenerator productGenerator() {
        return new ProductGenerator();
    }

    @Bean
    public ProductServiceInterface productService() {
        return new ProductService();
    }

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/WEB-INF/views/");
        internalResourceViewResolver.setSuffix(".jsp");
        return internalResourceViewResolver;
    }
}
