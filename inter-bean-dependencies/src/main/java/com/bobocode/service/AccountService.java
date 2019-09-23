package com.bobocode.service;

import com.bobocode.dao.AccountDao;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AccountService {
    private final AccountDao accountDao;

}
