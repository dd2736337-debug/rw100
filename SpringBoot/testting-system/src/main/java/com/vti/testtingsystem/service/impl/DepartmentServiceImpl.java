package com.vti.testtingsystem.service.impl;

import com.vti.testtingsystem.entity.Department;
import com.vti.testtingsystem.repository.IDepartmentRepository;
import com.vti.testtingsystem.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DepartmentServiceImpl implements IDepartmentService {

    @Autowired
    private IDepartmentRepository departmentRepository;


    @Override
    public List<Department> findAll() {
        List<Department> departments = departmentRepository.findAll();
        return departments;
    }

    @Override
    public Department findById(Integer id) {
        Department department = departmentRepository.findById(id).orElse(null);
        return department;
    }


    @Override
    public void deleteByID(Integer id) {
        departmentRepository.deleteById(id);

    }

    @Override
    public void create(Department department) {
        departmentRepository.save(department);
    }

    @Override
    public void update(Department department, Integer id) {
        Department departmentUpdate = departmentRepository.findById(id).orElse(null);
        if (Objects.isNull(departmentUpdate)) {
            throw new RuntimeException("ID not found");
        } else {
            departmentUpdate.setName(department.getName());
            departmentRepository.save(departmentUpdate);
        }
    }
}
