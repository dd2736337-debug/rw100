package com.vti.controller;

import com.vti.entity.Department;
import com.vti.service.IDepartmentService;
import com.vti.service.impl.DepartmentServiceImpl;

import java.util.List;

public class DepartmentController {
    private final IDepartmentService departmentService=new DepartmentServiceImpl();

    public List<Department> findAll() {
        return departmentService.findAll();
    }

    public Department findById(Integer id) {
        return departmentService.findById(id);
    }

    public void create(Department department) {
        departmentService.create(department);
    }

    public void update(Department department) {
        departmentService.update(department);
    }

    public void delete(Integer id) {
        departmentService.delete(id);

    }
}
