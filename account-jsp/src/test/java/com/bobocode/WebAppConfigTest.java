package com.bobocode;

import com.bobocode.config.RootConfig;
import com.bobocode.config.WebConfig;
import com.bobocode.util.WebAppInitializerWrapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.arrayContaining;
import static org.hamcrest.Matchers.arrayWithSize;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@SpringJUnitConfig(classes = RootConfig.class)
class WebAppConfigTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void testDispatcherServletMapping() {
        WebAppInitializerWrapper webAppInitializerWrapper = new WebAppInitializerWrapper();

        assertThat(webAppInitializerWrapper.getServletMappings(), arrayContaining("/"));
    }

    @Test
    void testInitializerRootConfigClasses() {
        WebAppInitializerWrapper webAppInitializerWrapper = new WebAppInitializerWrapper();

        assertThat(webAppInitializerWrapper.getRootConfigClasses(), arrayContaining(RootConfig.class));
    }

    @Test
    void testInitializerWebConfigClasses() {
        WebAppInitializerWrapper webAppInitializerWrapper = new WebAppInitializerWrapper();

        assertThat(webAppInitializerWrapper.getServletConfigClasses(), arrayContaining(WebConfig.class));
    }

    @Test
    void testRootConfigClassIsMarkedAsConfiguration() {
        Configuration configuration = RootConfig.class.getAnnotation(Configuration.class);

        assertThat(configuration, notNullValue());
    }

    @Test
    void testRootConfigClassEnablesComponentScan() {
        ComponentScan componentScan = RootConfig.class.getAnnotation(ComponentScan.class);

        assertThat(componentScan, notNullValue());
    }

    @Test
    void testRootConfigComponentScanPackages() {
        ComponentScan componentScan = RootConfig.class.getAnnotation(ComponentScan.class);

        assertThat(componentScan.basePackages(), arrayContaining("com.bobocode"));
    }

    @Test
    void testRootConfigComponentScanFilters() {
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
    void testWebConfigIsMarkedAsConfiguration() {
        Configuration configuration = WebConfig.class.getAnnotation(Configuration.class);

        assertThat(configuration, notNullValue());
    }

    @Test
    void testWebConfigEnablesComponentScan() {
        ComponentScan componentScan = WebConfig.class.getAnnotation(ComponentScan.class);

        assertThat(componentScan, notNullValue());
    }

    @Test
    void testWebConfigComponentScanPackages() {
        ComponentScan componentScan = WebConfig.class.getAnnotation(ComponentScan.class);

        assertThat(componentScan.value(), arrayContaining("com.bobocode.web"));
    }

    @Test
    void testWebConfigEnablesWebMvc() {
        EnableWebMvc enableWebMvc = WebConfig.class.getAnnotation(EnableWebMvc.class);

        assertThat(enableWebMvc, notNullValue());
    }

    @Test
    void testDataGeneratorBeanName() {
        TestDataGenerator dataGenerator = applicationContext.getBean("dataGenerator", TestDataGenerator.class);

        assertThat(dataGenerator, notNullValue());
    }

    @Test
    void testDataGeneratorBeanNameIsNotSpecifiedExplicitly() {
        Method[] methods = RootConfig.class.getMethods();
        Method testDataGeneratorBeanMethod = findTestDataGeneratorBeanMethod(methods);
        Bean bean = testDataGeneratorBeanMethod.getDeclaredAnnotation(Bean.class);

        assertThat(bean.name(), arrayWithSize(0));
        assertThat(bean.value(), arrayWithSize(0));
    }

    private Method findTestDataGeneratorBeanMethod(Method[] methods) {
        for (Method method : methods) {
            if (method.getReturnType().equals(TestDataGenerator.class)
                    && method.getDeclaredAnnotation(Bean.class) != null) {
                return method;
            }
        }
        return null;
    }

}
