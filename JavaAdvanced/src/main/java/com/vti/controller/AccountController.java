package com.vti.controller;

import com.vti.entity.Account;
import com.vti.service.IAccountService;
import com.vti.service.impl.AccountService;

import java.util.List;

public class AccountController {
    private IAccountService service = new AccountService();

    public List<Account> findAll() {
        return service.findAll();
    }

    Account findById(Integer id) {
        return service.findById(id);
    }

    void create(Account account) {
        service.create(account);
    }

    void update(Account account) {
        service.update(account);
    }

    void delete(Integer id) {
        service.delete(id);
    }
}
