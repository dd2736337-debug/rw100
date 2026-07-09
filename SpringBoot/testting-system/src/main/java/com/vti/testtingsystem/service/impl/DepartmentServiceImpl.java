package com.vti.testtingsystem.service.impl;

import com.vti.testtingsystem.dto.DepartmentDto;
import com.vti.testtingsystem.entity.Department;
import com.vti.testtingsystem.repository.IDepartmentRepository;
import com.vti.testtingsystem.service.IDepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements IDepartmentService {

    @Autowired
    private IDepartmentRepository departmentRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<DepartmentDto> findAll() {
        return departmentRepository.findAll().stream().map(dep -> modelMapper.map(dep, DepartmentDto.class)).toList();
    }

    @Override
    public DepartmentDto findById(Integer id) {
        Department department = departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Department không tồn tại"));
        return modelMapper.map(department, DepartmentDto.class);
    }

    @Override
    public void create(Department department) {
        //kiểm tra xen tên đã tồn tại chưa
        if (departmentRepository.existsByName(department.getName())) {
            throw new RuntimeException("Department tồn tại");
        }
        departmentRepository.save(department);

    }

    @Override
    public void update(Department department, Integer id) {
        Department departmentUpdate = departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Không tìm thấy Department"));
        if (departmentRepository.existsByNameAndIdNot(department.getName(), id)) {
            throw new RuntimeException("Department  tồn tại");
        }
        departmentUpdate.setName(department.getName());
        departmentRepository.save(departmentUpdate);
    }


    @Override
    public void deleteByID(Integer id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public DepartmentDto findByName(String name) {
        Department department = departmentRepository.findByName(name).orElseThrow(() -> new RuntimeException("Department không tồn tại"));
        return modelMapper.map(department, DepartmentDto.class);
    }


}
