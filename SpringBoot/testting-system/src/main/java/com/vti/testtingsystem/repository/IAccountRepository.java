package com.vti.testtingsystem.repository;

import com.vti.testtingsystem.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface IAccountRepository extends JpaRepository<Account, Integer>,JpaSpecificationExecutor<Account>{
    Account findByUserName(String userName);

    List<Account> findAllByFullName(String fullName);


    boolean existsByUserName(String userName);


    List<Account> findByFullNameAndUserName(String fullName, String userName);

    List<Account> findByFullNameOrUserName(String fullName, String userName);

    Optional<Account> findByFullName(String fullName);

    Optional<Account> findByEmail(String email);
    

    boolean existsByUserNameAndIdNot(String userName, Integer id);

    boolean existsByEmailAndIdNot(String email, Integer id);

    boolean existsByDepartmentId(Integer departmentId);

    boolean existsByPositionId(Integer positionId);


}
