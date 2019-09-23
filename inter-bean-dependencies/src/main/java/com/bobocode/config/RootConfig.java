package com.bobocode.config;

import com.bobocode.TestDataGenerator;
import com.bobocode.dao.AccountDao;
import com.bobocode.dao.impl.FakeAccountDao;
import com.bobocode.service.AccountService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * todo: Refactor {@link RootConfig} in order to user inter-bean dependencies properly.
 */
@Component
public final class RootConfig {

    @Bean
    public AccountService accountService() {
        return new AccountService(fakeAccountDao());
    }

    @Bean
    public final AccountDao fakeAccountDao() {
        return new FakeAccountDao(dataGenerator());
    }

    @Bean
    private TestDataGenerator dataGenerator() {
        return new TestDataGenerator();
    }
}
