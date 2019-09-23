package com.bobocode.dao.impl;

import com.bobocode.TestDataGenerator;
import com.bobocode.dao.AccountDao;
import com.bobocode.model.Account;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class FakeAccountDao implements AccountDao {
    private final TestDataGenerator dataGenerator;

    @Override
    public Account findById(Long id) {
        Account account = dataGenerator.generateAccount();
        account.setId(id);
        return account;
    }
}
