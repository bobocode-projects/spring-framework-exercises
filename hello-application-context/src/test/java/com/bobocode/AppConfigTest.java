package com.bobocode;

import com.bobocode.config.AppConfig;
import com.bobocode.dao.AccountDao;
import com.bobocode.dao.FakeAccountDao;
import com.bobocode.model.Account;
import com.bobocode.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


@SpringJUnitConfig
@ContextConfiguration
public class AppConfigTest {
    @Configuration
    @ComponentScan(basePackages = "com.bobocode")
    static class TestConfig {
    }

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountDao accountDao;

    @Test
    public void testConfigClassIsMarkedAsConfiguration() {
        Configuration configuration = AppConfig.class.getAnnotation(Configuration.class);

        assertThat(configuration, notNullValue());
    }

    @Test
    public void testComponentScanIsEnabled() {
        ComponentScan componentScan = AppConfig.class.getAnnotation(ComponentScan.class);

        assertThat(componentScan, notNullValue());
    }

    @Test
    public void testComponentScanPackagesAreSpecified() {
        ComponentScan componentScan = AppConfig.class.getAnnotation(ComponentScan.class);

        assertThat(componentScan.basePackages(), arrayContainingInAnyOrder("com.bobocode.dao", "com.bobocode.service"));
    }

    @Test
    public void testDataGeneratorHasOnlyOneBean() {
        Map<String, TestDataGenerator> testDataGeneratorMap = applicationContext.getBeansOfType(TestDataGenerator.class);

        assertThat(testDataGeneratorMap.size(), is(1));
    }

    @Test
    public void testDataGeneratorBeanIsConfiguredExplicitly() {
        Method[] methods = AppConfig.class.getMethods();
        Method testDataGeneratorBeanMethod = findTestDataGeneratorBeanMethod(methods);


        assertThat(testDataGeneratorBeanMethod, notNullValue());
    }

    @Test
    public void testDataGeneratorBeanName() {
        Map<String, TestDataGenerator> dataGeneratorBeanMap = applicationContext.getBeansOfType(TestDataGenerator.class);

        assertThat(dataGeneratorBeanMap.keySet(), hasItem("dataGenerator"));
    }

    @Test
    public void testDataGeneratorBeanNameIsNotSpecifiedExplicitly() {
        Method[] methods = AppConfig.class.getMethods();
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

    @Test
    public void testFakeAccountDaoIsConfiguredAsComponent() {
        Component component = FakeAccountDao.class.getAnnotation(Component.class);

        assertThat(component, notNullValue());
    }

    @Test
    public void testAccountDaoHasOnlyOneBean() {
        Map<String, AccountDao> accountDaoBeanMap = applicationContext.getBeansOfType(AccountDao.class);

        assertThat(accountDaoBeanMap.size(), is(1));
    }

    @Test
    public void testAccountDaoBeanName() {
        Map<String, AccountDao> accountDaoBeanMap = applicationContext.getBeansOfType(AccountDao.class);

        assertThat(accountDaoBeanMap.keySet(), hasItem("accountDao"));
    }

    @Test
    public void testAccountDaoConstructorIsMarkedWithAutowired() throws NoSuchMethodException {
        Autowired autowired = FakeAccountDao.class.getConstructor(TestDataGenerator.class).getAnnotation(Autowired.class);

        assertThat(autowired, notNullValue());
    }

    @Test
    public void testAccountServiceHasOnlyOneBean() {
        Map<String, AccountService> accountServiceMap = applicationContext.getBeansOfType(AccountService.class);

        assertThat(accountServiceMap.size(), is(1));
    }

    @Test
    public void testAccountServiceIsConfiguredAsService() {
        Service service = AccountService.class.getAnnotation(Service.class);

        assertThat(service, notNullValue());
    }

    @Test
    public void testAccountServiceBeanName() {
        Map<String, AccountService> accountServiceMap = applicationContext.getBeansOfType(AccountService.class);

        assertThat(accountServiceMap.keySet(), hasItem("accountService"));
    }

    @Test
    public void testAccountServiceBeanNameIsNotSpecifiedExplicitly() {
        Service service = AccountService.class.getAnnotation(Service.class);

        assertThat(service.value(), equalTo(""));
    }

    @Test
    public void testAccountServiceDoesNotUseAutowired() throws NoSuchMethodException {
        Annotation[] annotations = AccountService.class.getConstructor(AccountDao.class).getDeclaredAnnotations();

        assertThat(annotations, arrayWithSize(0));
    }

    @Test
    public void testFindRichestAccount() {
        Account richestAccount = accountService.findRichestAccount();

        Account actualRichestAccount = accountDao.findAll().stream().max(Comparator.comparing(Account::getBalance)).get();

        assertThat(richestAccount, equalTo(actualRichestAccount));
    }


}
