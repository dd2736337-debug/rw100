package com.vti.testtingsystem.repository;

import com.vti.testtingsystem.entity.Department;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IDepartmentRepository extends JpaRepository<Department,Integer> {
    Optional<Department> findByName(String name);
    
    //kiểm tra xem name đã tồn tại chưa

    boolean existsByName(String name);


    boolean existsByNameAndIdNot(String name, Integer id);


}
