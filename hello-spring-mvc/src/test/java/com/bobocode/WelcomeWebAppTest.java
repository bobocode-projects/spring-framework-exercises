package com.bobocode;

import com.bobocode.config.RootConfig;
import com.bobocode.config.WebConfig;
import com.bobocode.web.controller.WelcomeController;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class WelcomeWebAppTest {

    @Test
    public void testDispatcherServletMapping() {
        WebAppInitializerWrapper webAppInitializerWrapper = new WebAppInitializerWrapper();

        assertThat(webAppInitializerWrapper.getServletMappings(), arrayContaining("/"));
    }

    @Test
    public void testInitializerRootConfigClasses() {
        WebAppInitializerWrapper webAppInitializerWrapper = new WebAppInitializerWrapper();

        assertThat(webAppInitializerWrapper.getRootConfigClasses(), arrayContaining(RootConfig.class));
    }

    @Test
    public void testInitializerWebConfigClasses() {
        WebAppInitializerWrapper webAppInitializerWrapper = new WebAppInitializerWrapper();

        assertThat(webAppInitializerWrapper.getServletConfigClasses(), arrayContaining(WebConfig.class));
    }

    @Test
    public void testRootConfigClassIsMarkedAsConfiguration() {
        Configuration configuration = RootConfig.class.getAnnotation(Configuration.class);

        assertThat(configuration, notNullValue());
    }

    @Test
    public void testRootConfigClassEnablesComponentScan() {
        ComponentScan componentScan = RootConfig.class.getAnnotation(ComponentScan.class);

        assertThat(componentScan, notNullValue());
    }

    @Test
    public void testRootConfigComponentScanPackages() {
        ComponentScan componentScan = RootConfig.class.getAnnotation(ComponentScan.class);
        String[] packages = componentScan.basePackages();
        if (packages.length == 0) {
            packages = componentScan.value();
        }

        assertThat(packages, arrayContaining("com.bobocode"));
    }

    @Test
    public void testRootConfigComponentScanFilters() {
        ComponentScan componentScan = RootConfig.class.getAnnotation(ComponentScan.class);
        Filter[] filters = componentScan.excludeFilters();
        List<Class> filteredClasses = getFilteredClasses(filters);

        assertThat(filters, arrayWithSize(2));
        assertThat(filters[0].type(), equalTo(FilterType.ANNOTATION));
        assertThat(filters[1].type(), equalTo(FilterType.ANNOTATION));
        assertThat(filteredClasses, containsInAnyOrder(EnableWebMvc.class, Controller.class));
    }

    private List<Class> getFilteredClasses(Filter[] filters) {
        return Stream.of(filters).flatMap(filter -> Stream.of(filter.value())).collect(Collectors.toList());
    }

    @Test
    public void testWebConfigIsMarkedAsConfiguration() {
        Configuration configuration = WebConfig.class.getAnnotation(Configuration.class);

        assertThat(configuration, notNullValue());
    }

    @Test
    public void testWebConfigEnablesComponentScan() {
        ComponentScan componentScan = WebConfig.class.getAnnotation(ComponentScan.class);

        assertThat(componentScan, notNullValue());
    }

    @Test
    public void testWebConfigComponentScanPackages() {
        ComponentScan componentScan = WebConfig.class.getAnnotation(ComponentScan.class);

        assertThat(componentScan.basePackages(), arrayContaining("com.bobocode.web"));
    }

    @Test
    public void testWebConfigEnablesWebMvc() {
        EnableWebMvc enableWebMvc = WebConfig.class.getAnnotation(EnableWebMvc.class);

        assertThat(enableWebMvc, notNullValue());
    }

    @Test
    public void testWelcomeControllerIsMarkedAsController() {
        Controller controller = WelcomeController.class.getAnnotation(Controller.class);

        assertThat(controller, notNullValue());
    }

    @Test
    public void testWelcomeControllerMethodIsMarkedAsGetMethod() throws NoSuchMethodException {
        GetMapping getMapping = WelcomeController.class.getDeclaredMethod("welcome").getAnnotation(GetMapping.class);

        assertThat(getMapping, notNullValue());
    }

    @Test
    public void testWelcomeControllerMethodMapping() throws NoSuchMethodException {
        GetMapping getMapping = WelcomeController.class.getDeclaredMethod("welcome").getAnnotation(GetMapping.class);

        assertThat(getMapping.value(), arrayContaining("/welcome"));
    }
}
