package com.vti.testtingsystem.controller;

import com.vti.testtingsystem.dto.AccountDto;
import com.vti.testtingsystem.entity.Account;
import com.vti.testtingsystem.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountServiceImpl accountService;

    @GetMapping
    public List<Account> findAll() {
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    public Account findById(@PathVariable Integer id) {
        return accountService.findById(id);
    }


    @PostMapping
    public void create(@RequestBody AccountDto accountDto) {
        accountService.create(accountDto);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody AccountDto accountDto, @PathVariable Integer id) {
        accountService.update(accountDto, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        accountService.delete(id);
    }

}
