package com.bobocode.config;

import com.bobocode.TestDataGenerator;
import com.bobocode.dao.AccountDao;
import com.bobocode.dao.impl.FakeAccountDao;
import com.bobocode.service.AccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * todo: Refactor {@link RootConfig} in order to user inter-bean dependencies properly.
 */
@Configuration // @Configuration works a bit differently then @Component
// In this case Spring uses CGLIB to create a proxy in order to support inter-bean dependencies.
// It's when you call other method in your config class and still get the same bean instance
public class RootConfig { // Because CGLIB proxy is created as a subclass of your @Configuration class, RootConfig cannot be final

    @Bean
    public AccountService accountService() {
        return new AccountService(fakeAccountDao()); // Although you call fakeAccountDao() method here, it is not called directly
        // Spring creates proxy that overrides all these method. It caches created beans so when you call method like
        // fakeAccountDao() it first checks if such beans was created before.
        // It allows to support default bean scope SINGLETON.
    }

    @Bean
    public AccountDao fakeAccountDao() {
        return new FakeAccountDao(dataGenerator());
    }

    @Bean
    public TestDataGenerator dataGenerator() { // Because Spring overrides methods in a proxy,
        // bean methods in @Configuration classes cannot be final
        return new TestDataGenerator();
    }
}
