package com.bobocode.dao;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.stream.Stream;

import com.bobocode.TestDataGenerator;
import com.bobocode.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Provides a fake {@link AccountDao} implementation that uses generated fake data.
 * <p>
 * todo: configure this class as Spring component with bean name "accountDao"
 * todo: use explicit (with {@link Autowired} annotation) constructor-based dependency injection
 */
@Component(value = "accountDao")
public class FakeAccountDao implements AccountDao {
    private List<Account> accounts;

    @Autowired
    public FakeAccountDao(TestDataGenerator testDataGenerator) {
        this.accounts = Stream.generate(testDataGenerator::generateAccount)
                .limit(20)
                .collect(toList());
    }

    @Override
    public List<Account> findAll() {
        return accounts;
    }
}
