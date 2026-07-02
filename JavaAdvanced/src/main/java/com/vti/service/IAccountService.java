package com.vti.service;

import com.vti.entity.Account;
import com.vti.entity.Department;

import java.util.List;

public interface IAccountService {
    List<Account> findAll();

    Account findById(Integer id);

    void create(Account account);

    void update(Account account);

    void delete(Integer id);
}
