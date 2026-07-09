package com.vti.testtingsystem.service;

import com.vti.testtingsystem.dto.AccountDTO;
import com.vti.testtingsystem.form.AccountCreateAndUpdateForm;

import java.util.List;

public interface IAccountService {
    List<AccountDTO> findAll();

    AccountDTO findById(Integer id);

    AccountDTO findByFullName(String fullName);

    AccountDTO findByEmail(String email);

    void create(AccountCreateAndUpdateForm form);

    void update(AccountCreateAndUpdateForm form, Integer id);

    void delete(Integer id);
}
