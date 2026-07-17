package com.vti.testtingsystem.service;

import com.vti.testtingsystem.dto.AccountDTO;
import com.vti.testtingsystem.form.AccountCreateAndUpdateForm;
import com.vti.testtingsystem.form.AccountSearchForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAccountService {
    Page<AccountDTO> findAll (AccountSearchForm form, Pageable pageable);

    AccountDTO findById(Integer id);

    AccountDTO findByFullName(String fullName);

    AccountDTO findByEmail(String email);

    void create(AccountCreateAndUpdateForm form);

    void update(AccountCreateAndUpdateForm form, Integer id);

    void delete(Integer id);
}
