package com.vti.service.impl;

import com.vti.entity.Department;

import com.vti.repository.IDepartmentRepository;
import com.vti.repository.impl.DepartmentRepositoryImpl;
import com.vti.service.IDepartmentService;

import java.util.List;

public class DepartmentServiceImpl implements IDepartmentService {
    private final IDepartmentRepository repository = new DepartmentRepositoryImpl();

    @Override
    public List<Department> findAll() {
        return repository.findAll();
    }

    @Override
    public Department findById(Integer id) {
        return repository.findByID(id);
    }

    @Override
    public void create(Department department) {
        repository.create(department);

    }

    @Override
    public void update(Department department) {
        repository.update(department);

    }

    @Override
    public void delete(Integer id) {
        repository.delete(id);
    }
}
