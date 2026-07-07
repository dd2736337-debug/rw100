package com.vti.testtingsystem.service.impl;

import com.vti.testtingsystem.dto.DepartmentDto;
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
        return  departmentRepository.findAll();
    }

    @Override
    public Department findById(Integer id) {
        return departmentRepository.findById(id).orElse(null);
    }

    @Override
    public void create(DepartmentDto dto) {
        Department department=new Department();
        department.setName(dto.getName());
        departmentRepository.save(department);

    }

    @Override
    public void update(DepartmentDto dto, Integer id) {
        Department department=departmentRepository.findById(id).orElse(null);
        if(department !=null){
            department.setName(dto.getName());
            departmentRepository.save(department);
        }
    }


    @Override
    public void deleteByID(Integer id) {
        departmentRepository.deleteById(id);
    }


}
