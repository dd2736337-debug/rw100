package com.vti.testtingsystem.service;

import com.vti.testtingsystem.entity.Department;

import java.util.List;

public interface IDepartmentService {

    List<Department> findAll();

    Department findById(Integer id);


    void deleteByID(Integer id);

    void create(Department department);

    void update(Department department, Integer id);
}
