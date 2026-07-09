package com.vti.testtingsystem.service;

import com.vti.testtingsystem.dto.DepartmentDto;
import com.vti.testtingsystem.entity.Department;

import java.util.List;
import java.util.Optional;

public interface IDepartmentService {

    List<DepartmentDto> findAll();

    DepartmentDto findById(Integer id);


    void create(Department department);


    void update(Department department, Integer id);

    void deleteByID(Integer id);

     DepartmentDto findByName(String name);
}
