package com.vti.testtingsystem.service;

import com.vti.testtingsystem.dto.AccountDTO;
import com.vti.testtingsystem.form.AccountCreateAndUpdateForm;
import com.vti.testtingsystem.form.AccountSearchForm;

import java.util.List;

public interface IAccountService {
    List<AccountDTO> findAll (AccountSearchForm form);

    AccountDTO findById(Integer id);

    AccountDTO findByFullName(String fullName);

    AccountDTO findByEmail(String email);

    void create(AccountCreateAndUpdateForm form);

    void update(AccountCreateAndUpdateForm form, Integer id);

    void delete(Integer id);
}
