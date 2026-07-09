package com.vti.testtingsystem.service;

import com.vti.testtingsystem.dto.AccountDTO;

import java.util.List;

public interface IAccountService {
    List<AccountDTO> findAll();

    AccountDTO findById(Integer id);

    AccountDTO findByFullName(String fullName);

//    void create(AccountDto accountDto);
//
//    void update(AccountDto accountDto, Integer id);
//
//    void delete(Integer id);
}
