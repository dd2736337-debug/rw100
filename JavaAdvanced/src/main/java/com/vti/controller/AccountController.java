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

    public Account findById(Integer id) {
        return service.findById(id);
    }

    public void create(Account account) {
        service.create(account);
    }

    public void update(Account account) {
        service.update(account);
    }

    public void delete(Integer id) {
        service.delete(id);
    }
}
