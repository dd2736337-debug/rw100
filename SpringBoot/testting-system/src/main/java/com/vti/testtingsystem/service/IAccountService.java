package com.vti.testtingsystem.service;

import com.vti.testtingsystem.dto.AccountDto;
import com.vti.testtingsystem.entity.Account;

import java.util.List;

public interface IAccountService {
    List<Account> findAll();

    Account findById(Integer id);

    void create(AccountDto accountDto);

    void update(AccountDto accountDto, Integer id);

    void delete(Integer id);
}
