package com.bobocode;

import com.bobocode.config.RootConfig;
import com.bobocode.dao.impl.FakeAccountDao;
import com.bobocode.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class RootConfigHacksTest {

    @Test
    void rootConfigShouldNotUseComponentScan() {
        ComponentScan componentScan = RootConfig.class.getAnnotation(ComponentScan.class);

        assertThat(componentScan).isNull();
    }

    @Test
    void rootConfigShouldNotImportOtherConfigs() {
        Import importAnnotation = RootConfig.class.getAnnotation(Import.class);

        assertThat(importAnnotation).isNull();
    }

    @Test
    void accountServiceBeanShouldBeConfiguredExplicitly() {
        Annotation[] annotations = AccountService.class.getAnnotations();
        List<Class> annotationClasses = Stream.of(annotations).map(Annotation::annotationType).collect(Collectors.toList());

        assertThat(annotationClasses).doesNotContain(Component.class, Service.class);
    }

    @Test
    void fakeAccountDaoBeanShouldBeConfiguredExplicitly() {
        Annotation[] annotations = FakeAccountDao.class.getAnnotations();
        List<Class> annotationClasses = Stream.of(annotations).map(Annotation::annotationType).collect(Collectors.toList());

        assertThat(annotationClasses).doesNotContain(Component.class, Service.class);
    }

    @Test
    void rootConfigShouldUseInterBeanDependencies() {
        Method[] methods = RootConfig.class.getDeclaredMethods();

        assertThat(methods).noneMatch(method -> method.getParameterCount() > 0);
    }
}
