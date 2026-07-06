package com.vti.testtingsystem.repository;

import com.vti.testtingsystem.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDepartmentRepository extends JpaRepository<Department,Integer> {
}
