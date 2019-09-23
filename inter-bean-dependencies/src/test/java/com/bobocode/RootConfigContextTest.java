package com.bobocode;

import com.bobocode.config.RootConfig;
import com.bobocode.dao.impl.FakeAccountDao;
import com.bobocode.service.AccountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.assertj.core.api.Assertions.assertThat;


@SpringJUnitConfig(classes = RootConfig.class)
class RootConfigContextTest {

    @Autowired
    private AccountService accountService;

    @Autowired
    private FakeAccountDao accountDao;

    @Autowired
    private TestDataGenerator dataGenerator;

    @Test
    void dataGeneratorShouldHaveScopeSingleton() {
        assertThat(accountDao.getDataGenerator()).isEqualTo(dataGenerator);
    }

    @Test
    void accountDaoShouldHaveScopeSingleton() {
        assertThat(accountService.getAccountDao()).isEqualTo(accountDao);
    }
}
