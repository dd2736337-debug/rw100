package com.vti.testtingsystem.service.impl;

import com.vti.testtingsystem.dto.AccountDto;
import com.vti.testtingsystem.entity.Account;
import com.vti.testtingsystem.entity.Department;
import com.vti.testtingsystem.repository.IAccountRepository;
import com.vti.testtingsystem.repository.IDepartmentRepository;
import com.vti.testtingsystem.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private IAccountRepository repository;
    @Autowired
    private IDepartmentRepository departmentRepository;

    @Override
    public List<Account> findAll() {
        return repository.findAll();
    }

    @Override
    public Account findById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void create(AccountDto accountDto) {
        Department department = departmentRepository.findById(accountDto.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department không tồn tại"));

        Account account = new Account();
        account.setUserName(accountDto.getUserName());
        account.setFullName(accountDto.getFullName());
        account.setEmail(accountDto.getEmail());
        account.setDepartment(department);

        repository.save(account);
    }

    @Override
    public void update(AccountDto accountDto, Integer id) {
        Account account = repository.findById(id).orElseThrow(() -> new RuntimeException("Account không tồn tại"));
        Department department = departmentRepository.findById(accountDto.getDepartmentId()).orElseThrow(() -> new RuntimeException("Department không tồn tại"));
        account.setUserName(accountDto.getUserName());
        account.setFullName(accountDto.getFullName());
        account.setEmail(accountDto.getEmail());
        account.setDepartment(department);
        repository.save(account);

    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);

    }
}
