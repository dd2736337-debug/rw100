package com.vti.testtingsystem.service;

import com.vti.testtingsystem.dto.DepartmentDto;
import com.vti.testtingsystem.entity.Department;
import com.vti.testtingsystem.form.DepartmentCreateAndUpdateForm;

import java.util.Deque;
import java.util.List;
import java.util.Optional;

public interface IDepartmentService {

    List<DepartmentDto> findAll();

    DepartmentDto findById(Integer id);


    void create(DepartmentCreateAndUpdateForm form);


    void update(DepartmentCreateAndUpdateForm form, Integer id);

    void deleteByID(Integer id);

     DepartmentDto findByName(String name);
}
