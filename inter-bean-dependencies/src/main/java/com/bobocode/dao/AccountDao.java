package com.bobocode.dao;

import com.bobocode.model.Account;

public interface AccountDao {
    Account findById(Long id);
}
