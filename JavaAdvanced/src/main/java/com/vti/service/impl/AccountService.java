package com.vti.service.impl;

import com.vti.entity.Account;
import com.vti.repository.IAccountRepository;
import com.vti.repository.impl.AccountRepositoryImpl;
import com.vti.service.IAccountService;

import java.util.List;

public class AccountService implements IAccountService {
    private final IAccountRepository repository = new AccountRepositoryImpl();

    @Override
    public List<Account> findAll() {
        return repository.findAll();
    }

    @Override
    public Account findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public void create(Account account) {
        repository.create(account);
    }

    @Override
    public void update(Account account) {
        repository.update(account);
    }

    @Override
    public void delete(Integer id) {
        repository.delete(id);
    }
}
