package com.vti.testtingsystem.repository;

import com.vti.testtingsystem.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRepository extends JpaRepository<Account,Integer> {
}
