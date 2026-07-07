package com.vti.testtingsystem.service;

import com.vti.testtingsystem.dto.DepartmentDto;
import com.vti.testtingsystem.entity.Department;

import java.util.List;

public interface IDepartmentService {

    List<Department> findAll();

    Department findById(Integer id);


    void create(DepartmentDto dto);

    void update(DepartmentDto dto, Integer id);

    void deleteByID(Integer id);

}
