package com.vti.testtingsystem.controller;

import com.vti.testtingsystem.dto.AccountDto;
import com.vti.testtingsystem.entity.Account;
import com.vti.testtingsystem.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountServiceImpl accountService;

    @GetMapping
    public ResponseEntity<List<Account>> findAll() {
        return ResponseEntity.ok(accountService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(accountService.findById(id));
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody AccountDto accountDto) {
        accountService.create(accountDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("thêm mới thành công");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody AccountDto accountDto, @PathVariable Integer id) {
        accountService.update(accountDto, id);
        return ResponseEntity.ok("Update thành công");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        accountService.delete(id);
        return ResponseEntity.ok("Xóa Thành công");
    }

}
