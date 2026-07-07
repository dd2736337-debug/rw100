package com.vti.testtingsystem.controller;

import com.vti.testtingsystem.dto.DepartmentDto;
import com.vti.testtingsystem.entity.Department;
import com.vti.testtingsystem.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/departments")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;

    //lấy ra ds department

    @GetMapping
    public List<Department> findAll() {

        return departmentService.findAll();
    }

    //lấy ra thông tin department theo id
    @GetMapping("/{id}")
    public Department findById(@PathVariable Integer id) {
        return departmentService.findById(id);
    }

    //tạo mới 1 department
    @PostMapping
    public void create(@RequestBody DepartmentDto dto) {
        departmentService.create(dto);
    }

    //update
    @PutMapping("/{id}")
    public void update(@RequestBody DepartmentDto dto, @PathVariable Integer id) {
        departmentService.update(dto, id);
    }

    //Xóa
    @DeleteMapping("/{id}")
    public void deleteByID(@PathVariable Integer id) {
        departmentService.deleteByID(id);
    }

//    //tạo mới 1 department
//    @PostMapping
//    public ResponseEntity<String> create(@RequestBody Department department) {
//        departmentService.create(department);
//        return new ResponseEntity<>("Tạo mới thành công", HttpStatus.CREATED);
//    }

//    //xóa theo id
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteByID(@PathVariable Integer id) {
//        departmentService.deleteByID(id);
//        return new ResponseEntity<>("Xóa thành công", HttpStatus.OK);
//    }


    //update theo id
//    @PutMapping({"/{id}"})
//    public ResponseEntity<String> update(@RequestBody Department department, @PathVariable Integer id) {
//        departmentService.update(department, id);
//        return new ResponseEntity<>("Update thành công", HttpStatus.OK);
//    }

}
