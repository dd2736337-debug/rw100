package com.vti.testtingsystem.controller;

import com.vti.testtingsystem.entity.Department;
import com.vti.testtingsystem.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/departments")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;

    //lấy ra ds department

    @GetMapping
    public ResponseEntity<List<Department>> findAll() {

        List<Department> departments = departmentService.findAll();
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    //lấy ra thông tin department theo id
    @GetMapping("/{id}")
    public ResponseEntity<Department> findByID(@PathVariable Integer id) {
        Department department = departmentService.findById(id);
        return new ResponseEntity<>(department, HttpStatus.OK);
    }

    //xóa theo id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteByID(@PathVariable Integer id) {
        departmentService.deleteByID(id);
        return new ResponseEntity<>("Xóa thành công", HttpStatus.OK);
    }

    //tạo mới 1 department
    @PostMapping
    public ResponseEntity<String> create(@RequestBody Department department) {
        departmentService.create(department);
        return new ResponseEntity<>("Tạo mới thành công", HttpStatus.CREATED);
    }

    //update theo id
    @PutMapping({"/{id}"})
    public ResponseEntity<String> update(@RequestBody Department department, @PathVariable Integer id) {
        departmentService.update(department, id);
        return new ResponseEntity<>("Update thành công", HttpStatus.OK);
    }

}
